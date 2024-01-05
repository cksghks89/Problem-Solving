import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[][] map;
    private static int[][][] calcMap;
    private static int[][][] square;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        calcMap = new int[n + 1][m + 1][2];
        square = new int[n + 1][m + 1][2];

        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 1) {
                    calcMap[i][j][0] = calcMap[i][j - 1][0] + 1;
                    calcMap[i][j][1] = calcMap[i - 1][j][1] + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 0) continue;

                int h = Math.min(square[i - 1][j - 1][1], calcMap[i - 1][j][1]);
                int w = Math.min(square[i - 1][j - 1][0], calcMap[i][j - 1][0]);

                square[i][j][0] = h + 1;
                square[i][j][1] = w + 1;

                int min = Math.min(h + 1, w + 1);

                answer = Math.max(answer, min * min);
            }
        }

        System.out.println(answer);
    }
}
