import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static char[][] board;

    private static List<int[]> teacherList;
    private static boolean answer = false;

    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        teacherList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = st.nextToken().charAt(0);
                if (board[i][j] == 'T') {
                    teacherList.add(new int[]{i, j});
                }
            }
        }
        subSet(0, 0, 0);

        System.out.println(answer ? "YES" : "NO");
    }

    private static void subSet(int cnt, int x, int y) {
        if (answer) return;
        if (cnt == 3) {
            avoidMonitor();
            return;
        }
        if (!(0 <= x && x < N && 0 <= y && y < N)) return;
        if (board[x][y] != 'X') {
            subSet(cnt, x + ((y + 1) / N), (y + 1) % N);
            return;
        }

        board[x][y] = 'O';
        subSet(cnt + 1, x + ((y + 1) / N), (y + 1) % N);
        board[x][y] = 'X';

        subSet(cnt, x + ((y + 1) / N), (y + 1) % N);
    }

    private static void avoidMonitor() {
        for (int[] teacher : teacherList) {
            int x = teacher[0];
            int y = teacher[1];

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                while (isInRange(nx, ny)) {
                    if (board[nx][ny] == 'S') {
                        return;
                    }
                    if (board[nx][ny] == 'O') break;
                    nx += dx[i];
                    ny += dy[i];
                }
            }
        }
        answer = true;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}