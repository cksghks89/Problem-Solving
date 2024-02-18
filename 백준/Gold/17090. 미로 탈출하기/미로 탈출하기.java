import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;

    private static int[][] map;
    private static int[][] visited;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] mapping = new int[200];
        mapping['U'] = 0;
        mapping['R'] = 1;
        mapping['D'] = 2;
        mapping['L'] = 3;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        map = new int[N][M];
        visited = new int[N][M];    // -1 : 탈출불가, 0 : 방문안함, 1 : 방문함, 2 : 탈출가능

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = mapping[input.charAt(j)];
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    answer += bfs(i, j);
                }
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int x, int y) {
        List<int[]> visitList = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]] == -1) {
                visitList.forEach(o -> visited[o[0]][o[1]] = -1);
                return 0;
            }
            if (visited[cur[0]][cur[1]] == 2) {
                visitList.forEach(o -> visited[o[0]][o[1]] = 2);
                return visitList.size();
            }
            if (visited[cur[0]][cur[1]] == 1) continue;
            visited[cur[0]][cur[1]] = 1;
            visitList.add(cur);

            int nx = cur[0] + dx[map[cur[0]][cur[1]]];
            int ny = cur[1] + dy[map[cur[0]][cur[1]]];

            if (!isInRange(nx, ny)) {
                visitList.forEach(o -> visited[o[0]][o[1]] = 2);
                return visitList.size();
            }

            if (visited[nx][ny] != 1) {
                queue.offer(new int[]{nx, ny});
            }
        }

        visitList.forEach(o -> visited[o[0]][o[1]] = -1);
        return 0;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
