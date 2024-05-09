import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] looseHealth = new int[N];
        int[] gainHappy = new int[N];

        StringTokenizer health = new StringTokenizer(br.readLine());
        StringTokenizer happy = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            looseHealth[i] = Integer.parseInt(health.nextToken());
            gainHappy[i] = Integer.parseInt(happy.nextToken());
        }

        int[][] dp = new int[N][100];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 100; j++) {
                if (i == 0) {
                    for (int k = looseHealth[0]; k < 100; k++) dp[0][k] = gainHappy[0];
                    continue;
                }
                if (j < looseHealth[i]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - looseHealth[i]] + gainHappy[i]);
            }
        }

        System.out.println(dp[N - 1][99]);
    }
}
