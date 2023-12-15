import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            int answer = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int distance = y - x;

            int k = 0;

            // 증가시켰을 때 남은 거리가 전부 감소해도 안된다면? -> 증가 x
            // 유지시켰을 때 남은 거리가 전부 감소해도 안된다면? -> 유지 x
            // 나머지는 감소
            while (distance != 0) {
                answer += 1;
                if (distance - (k + 1) >= getSumRemain(k)) {
                    distance -= k + 1;
                    k += 1;
                } else if (distance - k >= getSumRemain(k - 1)) {
                    distance -= k;
                } else {
                    distance -= k - 1;
                    k -= 1;
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }

    private static int getSumRemain(int k) {
        return k * (k + 1) / 2;
    }
}
