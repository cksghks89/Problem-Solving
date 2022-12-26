/*
    Boj 11403. 경로 찾기
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11403_경로찾기 {
    static int N;
    static boolean[][] floydWarshall;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        floydWarshall = new boolean[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                floydWarshall[i][j] = st.nextToken().equals("1");
            }
        }

        setFloydWarshall();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(floydWarshall[i][j] ? "1 " : "0 ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void setFloydWarshall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (floydWarshall[j][k]) {
                        continue;
                    }
                    floydWarshall[j][k] = floydWarshall[j][i] && floydWarshall[i][k];
                }
            }
        }
    }
}
