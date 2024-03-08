import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (cur < 0) negative.add(-cur);
            else positive.add(cur);
        }

        Collections.sort(positive, (o1, o2) -> o2 - o1);
        Collections.sort(negative, (o1, o2) -> o2 - o1);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < positive.size(); i++) {
            if (i % M == 0) {
                list.add(positive.get(i));
            }
        }

        for (int i = 0; i < negative.size(); i++) {
            if (i % M == 0) {
                list.add(negative.get(i));
            }
        }

        int answer = 0;
        Collections.sort(list);
        for (int i = 0; i < list.size() - 1; i++) {
            answer += list.get(i) * 2;
        }
        answer += list.get(list.size() - 1);

        System.out.println(answer);
    }
}
