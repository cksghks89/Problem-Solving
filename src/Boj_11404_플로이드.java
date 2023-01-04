/*
    Boj 11404. 플로이드
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11404_플로이드 {
    static int[][] floyd;
    static int n, m;

    static final int INF = 20_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        floyd = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                floyd[i][j] = i == j ? 0 : INF;
            }
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            floyd[a][b] = Math.min(floyd[a][b], c);
        }
        setFloyd();

        print();
    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(floyd[i][j] == INF){
                    floyd[i][j] = 0;
                }
                sb.append(floyd[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static void setFloyd() {
        // i : by
        for (int i = 1; i <= n; i++) {
            // j : from
            for (int j = 1; j <= n; j++) {
                // k : to
                for (int k = 1; k <= n; k++) {
                    floyd[j][k] = Math.min(floyd[j][k], floyd[j][i] + floyd[i][k]);
                }
            }
        }
    }
}
