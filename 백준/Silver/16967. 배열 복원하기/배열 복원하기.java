import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int H, W, X, Y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        int[][] raw = new int[H + X][W + Y];
        for (int i = 0; i < H + X; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W + Y; j++) {
                raw[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] A = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                A[i][j] = raw[i][j];
            }
        }

        for (int i = X; i < H; i++) {
            for (int j = Y; j < W; j++) {
                A[i][j] = A[i][j] - A[i - X][j - Y];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(A[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
