import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N, d, k, c;
    private static int[] sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[2 * N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N; i < 2 * N; i++) {
            sushi[i] = sushi[i - N];
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);
        }

        int p1 = 0;
        int p2 = k - 1;

        int answer = 0;
        while (p2 < 2 * N) {
            answer = Math.max(answer, map.size() + (map.containsKey(c) ? 0 : 1));

            if (map.containsKey(sushi[p1])) {
                map.put(sushi[p1], map.get(sushi[p1]) - 1);
                if (map.get(sushi[p1]) == 0) {
                    map.remove(sushi[p1]);
                }
            }
            p1 += 1;
            p2 += 1;

            if (p2 == 2 * N) break;
            map.put(sushi[p2], map.getOrDefault(sushi[p2], 0) + 1);
        }

        System.out.println(answer);
    }
}
