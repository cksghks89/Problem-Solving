import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, R;
    private static int[][] board;
    private static char[][] status;

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static int[] mapping = new int['Z'];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        status = new char[N][M];
        mapping['E'] = 0;
        mapping['W'] = 1;
        mapping['S'] = 2;
        mapping['N'] = 3;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                status[i][j] = 'S';
            }
        }

        int point = 0;
        for (int i = 0; i < R; i++) {
            // 1. 공격
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()) - 1;
            int Y = Integer.parseInt(st.nextToken()) - 1;
            char D = st.nextToken().charAt(0);
            point += attack(X, Y, mapping[D]);

            // 2. 수비
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken()) - 1;
            Y = Integer.parseInt(st.nextToken()) - 1;
            status[X][Y] = 'S';
        }

        System.out.println(point);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(status[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int attack(int x, int y, int d) {
        int count = board[x][y];

        int nx = x;
        int ny = y;
        for (int i = 1; i < count; i++) {
            nx = nx + dx[d];
            ny = ny + dy[d];
            if (!isInRange(nx, ny)) break;
            if (status[nx][ny] == 'S') {
                count = Math.max(count, i + board[nx][ny]);
            }
        }

        int point = 0;
        nx = x;
        ny = y;
        for (int i = 0; i < count; i++) {
            if (!isInRange(nx, ny)) break;
            if (status[nx][ny] == 'S') point += 1;
            status[nx][ny] = 'F';
            nx += dx[d];
            ny += dy[d];
        }
        return point;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
