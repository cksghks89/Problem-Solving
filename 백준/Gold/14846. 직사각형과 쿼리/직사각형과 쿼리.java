import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] mat;
    private static int[][][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        mat = new int[N + 1][N + 1];
        memo = new int[N + 1][N + 1][11];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k <= 10; k++) {
                    memo[i][j][k] = memo[i - 1][j][k] + memo[i][j - 1][k] - memo[i - 1][j - 1][k];
                }
                memo[i][j][mat[i][j]] += 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int[] count = new int[11];
            for (int j = 0; j <= 10; j++) {
                count[j] = memo[x2][y2][j];
                count[j] -= memo[x2][y1 - 1][j];
                count[j] -= memo[x1 - 1][y2][j];
                count[j] += memo[x1 - 1][y1 - 1][j];
            }

            int curAnswer = 0;
            for (int j = 1; j <= 10; j++) {
                if (count[j] > 0) curAnswer += 1;
            }

            sb.append(curAnswer).append('\n');
        }
        System.out.println(sb);
    }
}
