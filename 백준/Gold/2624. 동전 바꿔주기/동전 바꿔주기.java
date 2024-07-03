import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int T, k;
    private static int[][] coin;
    private static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        coin = new int[k][2];

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coin[i][0] = Integer.parseInt(st.nextToken());
            coin[i][1] = Integer.parseInt(st.nextToken());
        }

        memo = new int[k][T + 1];

        for (int i = 1; i <= coin[0][1]; i++) {
            if (i * coin[0][0] > T) break;
            memo[0][i * coin[0][0]] = 1;
        }

        for (int i = 1; i < k; i++) {
            for (int k = 1; k <= coin[i][1]; k++) {
                if (k * coin[i][0] > T) break;
                memo[i][k * coin[i][0]] += 1;
            }
            for (int j = 0; j <= T; j++) {

                int sum = 0;
                for (int k = 0; k <= coin[i][1]; k++) {
                    if (j - k * coin[i][0] < 0) break;
                    sum += memo[i - 1][j - k * coin[i][0]];
                }

                memo[i][j] += sum;
            }
        }

        System.out.println(memo[k - 1][T]);

    }
}
