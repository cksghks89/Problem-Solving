import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                int cur = line.charAt(j) - '0';
                board[i][j] = cur;
            }
        }

        int answer = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 1; k < Math.max(N, M); k++) {
                    int area = getArea(i, j, k);
                    answer = Math.max(answer, area);
                }
            }
        }

        System.out.println(answer);
    }

    private static int getArea(int x, int y, int w) {
        int num = board[x][y];

        if (!isInRange(x + w, y) || !isInRange(x, y + w) || !isInRange(x + w, y + w)) {
            return 0;
        }

        if (board[x + w][y] == num && board[x][y + w] == num && board[x + w][y + w] == num) {
            return (w + 1) * (w + 1);
        }

        return 0;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
