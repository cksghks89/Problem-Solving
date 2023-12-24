import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, M;
    private static char[][] map;
    private static int[][] visited;
    private static int[][] move;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new int[N][M];
        move = new int['Z'][2];
        move['U'] = new int[]{-1, 0};
        move['D'] = new int[]{1, 0};
        move['L'] = new int[]{0, -1};
        move['R'] = new int[]{0, 1};

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        parent = new int[N * M + 1];
        for (int i = 1; i <= N * M; i++) {
            parent[i] = i;
        }

        int group = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] != 0) continue;
                bfs(i, j, group++);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < group; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }

    private static void bfs(int x, int y, int group) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]] != 0) continue;
            visited[cur[0]][cur[1]] = group;

            int[] next = move[map[cur[0]][cur[1]]];
            int nx = cur[0] + next[0];
            int ny = cur[1] + next[1];

            if (visited[nx][ny] == 0) {
                queue.offer(new int[]{nx, ny});
            }

            if (visited[nx][ny] != 0 && visited[nx][ny] != group) {
                union(visited[nx][ny], group);
            }
        }
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;

        parent[pb] = pa;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

}
