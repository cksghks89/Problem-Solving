/*
    Boj 2638. 치즈
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_2638_치즈 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(x);
            return result * 31 + Integer.hashCode(y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point o = (Point) obj;
                return (o.x == this.x) && (o.y == this.y);
            }
            return false;
        }
    }

    static Set<Point> cheesePoint;

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int cheese;
    static int time;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cheesePoint = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int cur = Integer.parseInt(st.nextToken());
                if (cur == 1) {
                    cheese += 1;
                    cheesePoint.add(new Point(i, j));
                }
                map[i][j] = cur;
            }
        }

        while (cheese > 0) {
            visited = new boolean[N][M];
            time += 1;
            bfs();
        }

        System.out.println(time);
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        int[][] contact = new int[N][M];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny)) {
                    if (map[nx][ny] == 1) {
                        contact[nx][ny] += 1;
                    } else {
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        ArrayList<Point> remove = new ArrayList<>();
        for (Point cur : cheesePoint) {
            if (contact[cur.x][cur.y] >= 2) {
                cheese -= 1;
                map[cur.x][cur.y] = 0;
                remove.add(cur);
            }
        }
        remove.forEach(cheesePoint::remove);
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < M);
    }
}
