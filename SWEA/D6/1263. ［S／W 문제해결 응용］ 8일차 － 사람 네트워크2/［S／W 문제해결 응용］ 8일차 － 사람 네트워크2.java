import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final int INF = 11_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int[][] mat = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    mat[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append('#').append(tc).append(' ').append(floyd(N, mat)).append('\n');
        }
        System.out.print(sb);
    }

    static int floyd(int N, int[][] mat) {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
            for (int j = 0; j < N; j++) {
                if (mat[i][j] != 0) {
                    dist[i][j] = mat[i][j];
                }
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j || i == k) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int curSum = 0;
            for (int j = 0; j < N; j++) {
                if (dist[i][j] == INF) continue;
                curSum += dist[i][j];
            }

            sum = Math.min(sum, curSum);
        }

        return sum;
    }
}
