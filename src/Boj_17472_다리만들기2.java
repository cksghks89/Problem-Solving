/*
    Boj 17472. 다리 만들기 2
    level. gold 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Boj_17472_다리만들기2 {
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> edges;
    static boolean[][] visited;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int id = 1;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        edges = new ArrayList<>();
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        setAllEdge();
        parent = IntStream.range(0, id).toArray();

        edges.sort((o1, o2) -> o1[2] - o2[2]);
        int cost = 0;
        for (int[] edge : edges) {
            if (union(edge[0], edge[1])) {
                cost += edge[2];
            }
        }

        int check = find(parent[1]);
        for (int i = 2; i < id; i++) {
            if (check != find(i)) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(cost);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;

        parent[bRoot] = aRoot;
        return true;
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void setAllEdge() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    for (int d = 0; d < 4; d++) {
                        findEdge(i, j, d);
                    }
                }
            }
        }
    }

    static void findEdge(int x, int y, int d) {
        int len = 0;
        int nx = x + dx[d];
        int ny = y + dy[d];

        int connect = 0;
        while (isInRange(nx, ny)) {
            if (map[nx][ny] == 0) {
                len += 1;
                nx += dx[d];
                ny += dy[d];
                continue;
            }

            if (map[nx][ny] != map[x][y] && map[nx][ny] > 0) {
                connect = map[nx][ny];
            }
            break;
        }

        if (connect != 0 && len >= 2) {
            edges.add(new int[]{map[x][y], connect, len});
        }
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            map[cur[0]][cur[1]] = id;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (isInRange(nx, ny) && map[nx][ny] > 0 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        id++;
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < M);
    }
}