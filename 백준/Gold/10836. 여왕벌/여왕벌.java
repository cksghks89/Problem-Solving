import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int M, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] map = new int[M][M];
        for (int i = 0; i < M; i++) Arrays.fill(map[i], 1);

        int[] acc = new int[2 * M - 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            for (int j = zero; j < zero + one; j++) {
                acc[j] += 1;
            }
            for (int j = zero + one; j < 2 * M - 1; j++) {
                acc[j] += 2;
            }
        }

        int idx = 0;
        for (int i = M - 1; i > 0; i--) {
            map[i][0] += acc[idx++];
        }
        for (int i = 0; i < M; i++) {
            map[0][i] += acc[idx++];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                map[i][j] = map[i - 1][j];
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
