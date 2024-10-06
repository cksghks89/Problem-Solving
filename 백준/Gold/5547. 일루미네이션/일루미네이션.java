import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int W, H;
    private static int[][] board;
    private static boolean[][] visited;

    private static int[] dx = {-1, -1, 0, 0, 1, 1};
    private static int[] evenDy = {0, 1, -1, 1, 0, 1};
    private static int[] oddDy = {-1, 0, -1, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][W];
        visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!visited[i][j]) {
                    int count = bfs(i, j, board[i][j]);
                    if (board[i][j] == 1) answer += count;
                    else if (board[i][j] == 0) answer -= count;
                }
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int x, int y, int state) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        int building = 0;
        int adjacent = 0;
        boolean isInside = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            building += 1;

            if (state == 0 && (cur[0] == 0 || cur[0] == H - 1 || cur[1] == 0 || cur[1] == W - 1)) {
                isInside = false;
            }

            for (int i = 0; i < 6; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[0] % 2 == 0 ? cur[1] + evenDy[i] : cur[1] + oddDy[i];

                if (isInRange(nx, ny) && board[nx][ny] == state) {
                    queue.offer(new int[]{nx, ny});
                    adjacent += 1;
                }
            }
        }

        if (state == 0 && !isInside) {
            return 0;
        } else {
            return building * 6 - adjacent;
        }
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }
}
