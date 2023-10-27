import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static char[][] map;
    private static List<int[]> landList;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        landList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'L') {
                    landList.add(new int[]{i, j});
                }
            }
        }

        int result = 0;
        for (int i = 0; i < landList.size(); i++) {
            result = Math.max(result, bfs(i));
        }
        System.out.println(result);
    }

    private static int bfs(int s) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{landList.get(s)[0], landList.get(s)[1], 0});

        boolean[][] visited = new boolean[N][M];

        int distance = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            distance = Math.max(distance, cur[2]);
            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == 'L' && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }

        return distance;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
