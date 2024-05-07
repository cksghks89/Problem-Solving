import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int C, N;
    private static int[][] advertisement;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        advertisement = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            advertisement[i] = new int[]{cost, customer};
        }

        // 해당 돈을 썼을때 얻을 수 있는 최대 고객 수

        int[] dp = new int[110_000];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < advertisement.length; j++) {
                int cost = advertisement[j][0];
                int customer = advertisement[j][1];

                if (cost > i) continue;
                dp[i] = Math.max(Math.max(dp[i], dp[i - 1]), dp[i - cost] + customer);
            }
            if (dp[i] >= C) {
                System.out.println(i);
                return;
            }
        }
    }
}
