import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int DISAPPEAR = 10;

    private static int N, K;
    private static int[][] board;
    private static boolean[][] visited;

    private static List<int[]> list = new ArrayList<>();
    private static Queue<int[]> queue = new ArrayDeque<>();

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][10];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < 10; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        // 1. K개 이상 연결된 부분 탐색
        // 2. 찾은 부분 제거
        // 3. 중력
        // 1 ~ 3 반복 (없을 때까지)
        while (true) {
            boolean out = true;

            visited = new boolean[N][10];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board[i][j] == 0) continue;
                    if (visited[i][j]) continue;
                    boolean result = bfs(i, j);
                    if (result) out = false;
                }
            }

            if (out) break;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board[i][j] == DISAPPEAR) {
                        board[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i < 10; i++) {
                Queue<Integer> lineQueue = new ArrayDeque<>();
                for (int j = N - 1; j >= 0; j--) {
                    if (board[j][i] != 0) {
                        lineQueue.offer(board[j][i]);
                        board[j][i] = 0;
                    }
                }

                int idx = N - 1;
                while (!lineQueue.isEmpty()) {
                    board[idx--][i] = lineQueue.poll();
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                answer.append(board[i][j]);
            }
            answer.append('\n');
        }

        System.out.println(answer);
    }

    private static boolean bfs(int x, int y) {
        queue.clear();
        list.clear();
        int target = board[x][y];

        queue.offer(new int[]{x, y});
        int count = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            count += 1;
            list.add(new int[]{cur[0], cur[1]});

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && board[nx][ny] == target) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        if (count >= K) {
            for (int[] cur : list) {
                board[cur[0]][cur[1]] = DISAPPEAR;
            }
            return true;
        }

        return false;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < 10;
    }
}
