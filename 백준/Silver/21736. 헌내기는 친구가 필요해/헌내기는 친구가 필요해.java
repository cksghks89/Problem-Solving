import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static char[][] map;
    private static boolean[][] visited;

    private static int N, M;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        int[] start = new int[2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'I') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        int count = bfs(start);
        System.out.println(count == 0 ? "TT" : count);
    }

    private static int bfs(int[] start) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(start);

        int count = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            if (map[cur[0]][cur[1]] == 'P') {
                count += 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != 'X') {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return count;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
