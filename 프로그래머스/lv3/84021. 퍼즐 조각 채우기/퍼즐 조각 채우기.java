import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    static int N;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int result;

    public int solution(int[][] game_board, int[][] table) {
        N = game_board.length;
        visited = new boolean[N][N];
        result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (game_board[i][j] == 1 || visited[i][j]) continue;
                List<int[]> cur = bfs(i, j, game_board, visited, 0);
                find(game_board, table, cur);
            }
        }

        return result;
    }

    private void find(int[][] game_board, int[][] table, List<int[]> cur) {
        for (int t = 0; t < 4; t++) {
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (table[i][j] == 0 || visited[i][j]) continue;
                    List<int[]> find = bfs(i, j, table, visited, 1);

                    if (isCorrect(cur, find)) {
                        result += cur.size();
                        for (int k = 0; k < cur.size(); k++) {
                            game_board[cur.get(k)[0]][cur.get(k)[1]] = 1;
                            table[find.get(k)[0]][find.get(k)[1]] = 0;
                        }
                        return;
                    }
                }
            }
            rotate(table);
        }
    }

    private boolean isCorrect(List<int[]> cur, List<int[]> find) {
        if (cur.size() != find.size()) return false;

        int dx = cur.get(0)[0] - find.get(0)[0];
        int dy = cur.get(0)[1] - find.get(0)[1];
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i)[0] - find.get(i)[0] != dx || cur.get(i)[1] - find.get(i)[1] != dy) {
                return false;
            }
        }

        return true;
    }

    private void rotate(int[][] table) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = table[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                table[i][j] = copy[N - 1 - j][i];
            }
        }
    }

    private List<int[]> bfs(int x, int y, int[][] map, boolean[][] visited, int val) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        List<int[]> frac = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            frac.add(new int[]{cur[0], cur[1]});

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == val && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return frac;
    }

    private boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}