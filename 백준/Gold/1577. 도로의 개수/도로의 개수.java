import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static List<int[]>[][] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 2][M + 2];
        dp[1][1] = 1;

        list = new List[102][102];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) + 1;
            int b = Integer.parseInt(st.nextToken()) + 1;
            int c = Integer.parseInt(st.nextToken()) + 1;
            int d = Integer.parseInt(st.nextToken()) + 1;

            if (list[c][d] == null) list[c][d] = new ArrayList<>();
            if (list[a][b] == null) list[a][b] = new ArrayList<>();
            list[c][d].add(new int[]{a, b});
            list[a][b].add(new int[]{c, d});
        }

        for (int i = 1; i <= N + 1; i++) {
            for (int j = 1; j <= M + 1; j++) {
                if (i == 1 && j == 1) continue;
                boolean up = isPossible(i, j, i - 1, j);
                boolean left = isPossible(i, j, i, j - 1);

                if (up && left) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else if (up) {
                    dp[i][j] = dp[i - 1][j];
                } else if (left) {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[N + 1][M + 1]);
    }

    private static boolean isPossible(int x, int y, int a, int b) {
        if (list[x][y] == null) return true;

        for (int i = 0; i < list[x][y].size(); i++) {
            int[] cur = list[x][y].get(i);
            if (cur[0] == a && cur[1] == b) return false;
        }
        return true;
    }
}
