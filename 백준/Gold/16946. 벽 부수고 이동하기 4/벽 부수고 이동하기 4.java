import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] cntMap;
    static Point[][] parents;
    static boolean[][] visited;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cntMap = new int[N][M];
        parents = new Point[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                parents[i][j] = new Point(i, j);
            }
        }

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        int[][] ans = getResult();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans[0].length; j++){
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int[][] getResult() {
        int[][] result = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    Set<Point> set = new HashSet<>();
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(isInRange(nx, ny) && map[nx][ny] == 0){
                            Point p = find(nx, ny);
                            if(set.contains(p)){
                                continue;
                            }
                            set.add(p);
                            cnt += cntMap[p.x][p.y];
                        }
                    }
                    result[i][j] = (cnt + 1) % 10;
                }
            }
        }
        return result;
    }

    static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        int cnt = 0;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (visited[cur.x][cur.y]) {
                continue;
            }
            visited[cur.x][cur.y] = true;
            cnt += 1;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 0) {
                    union(cur.x, cur.y, nx, ny);
                    queue.offer(new Point(nx, ny));
                }
            }
        }
        cntMap[x][y] = cnt;
    }

    static Point find(int x, int y) {
        Point p = parents[x][y];

        if (p.x == x && p.y == y) {
            return p;
        }

        return parents[x][y] = find(p.x, p.y);
    }

    static void union(int x1, int y1, int x2, int y2) {
        Point p1 = find(x1, y1);
        Point p2 = find(x2, y2);

        if (p1.x == p2.x && p1.y == p2.y) {
            return;
        }

        parents[p2.x][p2.y] = p1;
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < M);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(this.x);
            return 31 * result + Integer.hashCode(this.y);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point){
                Point o = (Point) obj;
                return this.x == o.x && this.y == o.y;
            }
            return false;
        }
    }
}