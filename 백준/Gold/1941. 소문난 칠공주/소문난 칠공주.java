import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    private static int answer = 0;
    private static char[][] board;
    private static boolean[][] select;

    private static Queue<int[]> queue;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[5][5];
        select = new boolean[5][5];
        queue = new ArrayDeque<>();

        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        subSet(0, 0, 0);

        System.out.println(answer);
    }

    private static void subSet(int cnt, int idx, int yCount) {
        if (cnt == 7) {
            answer += isPossible() ? 1 : 0;
            return;
        }
        if (idx == 25 || yCount >= 4) return;

        select[idx / 5][idx % 5] = true;
        subSet(cnt + 1, idx + 1, board[idx / 5][idx % 5] == 'Y' ? yCount + 1 : yCount);
        select[idx / 5][idx % 5] = false;

        subSet(cnt, idx + 1, yCount);
    }

    private static boolean isPossible() {
        queue.clear();

        for (int i = 0; i < 5; i++) {
            if (!queue.isEmpty()) break;
            for (int j = 0; j < 5; j++) {
                if (select[i][j]) {
                    queue.offer(new int[]{i, j});
                    break;
                }
            }
        }

        boolean[][] visited = new boolean[5][5];

        int s = 0;
        int y = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (board[cur[0]][cur[1]] == 'S') s++;
            else y++;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && select[nx][ny] && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return (s + y == 7) && (s >= 4);
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < 5 && 0 <= y && y < 5;
    }
}
