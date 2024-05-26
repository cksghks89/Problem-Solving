import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[][] log;
    private static int[][] board;

    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        board = new int[7][7];

        // 1 : 흑돌, 2 : 백돌
        board[3][3] = 2;
        board[4][4] = 2;
        board[3][4] = 1;
        board[4][3] = 1;

        int color = 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            doTurn(color, r, c);
            color = color == 1 ? 2 : 1;
        }

        int blackCnt = 0;
        int whiteCnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                if (board[i][j] == 0) sb.append('.');
                else if (board[i][j] == 1) {
                    sb.append('B');
                    blackCnt += 1;
                } else {
                    sb.append('W');
                    whiteCnt += 1;
                }
            }
            if (i != 6) {
                sb.append('\n');
            }
        }

        System.out.println(sb);
        if (blackCnt < whiteCnt) System.out.println("White");
        else System.out.println("Black");
    }

    private static void doTurn(int color, int x, int y) {
        board[x][y] = color;

        // 8방향을 탐색하여 모두 뒤집어야 함.
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            List<int[]> temp = new ArrayList<>();
            boolean change = false;
            while (isInRange(nx, ny)) {
                if (board[nx][ny] == color) {
                    change = true;
                    break;
                } else if (board[nx][ny] == 0) {
                    break;
                }
                temp.add(new int[]{nx, ny});
                nx += dx[i];
                ny += dy[i];
            }

            if (change && !temp.isEmpty()) {
                for (int[] t : temp) {
                    board[t[0]][t[1]] = color;
                }
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 1 <= x && x <= 6 && 1 <= y && y <= 6;
    }
}
