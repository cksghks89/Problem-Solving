import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, d, k, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N + k];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        for (int i = N; i < N + k; i++) {
            sushi[i] = sushi[i - N];
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(c, 1);

        int cnt = Integer.MIN_VALUE;
        for (int i = 0; i < sushi.length; i++) {
            if (i < k) {
                map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);
                if (i == k - 1) cnt = map.size();
                continue;
            }

            if (map.get(sushi[i - k]) == 1) {
                map.remove(sushi[i - k]);
            } else {
                map.put(sushi[i - k], map.get(sushi[i - k]) - 1);
            }
            map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);

            cnt = Math.max(cnt, map.size());
        }

        System.out.println(cnt);
    }
}
