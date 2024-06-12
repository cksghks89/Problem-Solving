import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static boolean[][][] visited;
    private static char[][] board;

    private static Set<Point> end;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        end = new HashSet<>();

        int[] start = new int[2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);

                if (board[i][j] == '0') {
                    board[i][j] = '.';
                    start = new int[]{i, j};
                } else if (board[i][j] == '1') {
                    board[i][j] = '.';
                    end.add(new Point(i, j));
                }
            }
        }

        int answer = bfs(start);
        System.out.println(answer);
    }

    private static int bfs(int[] start) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start[0], start[1], 0, 0});   // x좌표, y좌표, 이동횟수, key

        visited = new boolean[N][M][1 << 5 + 1];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]][cur[3]]) continue;
            visited[cur[0]][cur[1]][cur[3]] = true;

            if (end.contains(new Point(cur[0], cur[1]))) return cur[2];

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && board[nx][ny] != '#') {
                    if (board[nx][ny] == '.') {
                        queue.offer(new int[]{nx, ny, cur[2] + 1, cur[3]});
                    } else if ('a' <= board[nx][ny] && board[nx][ny] <= 'f') {
                        queue.offer(new int[]{nx, ny, cur[2] + 1, (cur[3] | (1 << (board[nx][ny] - 'a')))});
                    } else if ('A' <= board[nx][ny] && board[nx][ny] <= 'F') {
                        if ((cur[3] & (1 << (board[nx][ny] - 'A'))) > 0) {
                            queue.offer(new int[]{nx, ny, cur[2] + 1, cur[3]});
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
