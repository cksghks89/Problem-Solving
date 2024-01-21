import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] accSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        accSum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                accSum[i][j] = accSum[i][j - 1] + accSum[i - 1][j] - accSum[i - 1][j - 1] + map[i - 1][j - 1];
            }
        }

        for (int i = 0; i < M; i++) {
            int[] in = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int x1 = in[0];
            int y1 = in[1];
            int x2 = in[2];
            int y2 = in[3];

            sb.append(accSum[x2][y2] -
                    accSum[x2][y1 - 1] -
                    accSum[x1 - 1][y2] +
                    accSum[x1 - 1][y1 - 1]).append("\n");
        }
        System.out.print(sb.toString());
    }

}
