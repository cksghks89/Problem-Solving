import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static int[][] dist;
    static final int INF = 11_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            dist = new int[N + 1][N + 1];
            for (int i = 1; i <= M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                dist[a][b] = 1;
            }

            floyd();
            sb.append('#').append(tc).append(' ').append(getCnt()).append('\n');
        }
        System.out.print(sb);
    }

    static int getCnt() {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            int cur = 0;
            for (int j = 1; j <= N; j++) {
                cur += dist[i][j];
                cur += dist[j][i];
            }
            if (cur == N - 1) cnt++;
        }
        return cnt;
    }

    static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j || i == k || dist[i][j] == 1) continue;
                    if (dist[i][k] == 1 && dist[k][j] == 1) {
                        dist[i][j] = 1;
                    }
                }
            }
        }
    }
}
