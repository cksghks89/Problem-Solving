import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static List<int[]> bingsan;
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {1, 0, -1, 0};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        bingsan = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0) {
                    bingsan.add(new int[]{i, j});
                }
            }
        }

        int year = 0;
        while (!bingsan.isEmpty()) {
            int divideCnt = 0;
            visited = new boolean[N][M];
            for (int i = 0; i < bingsan.size(); i++) {
                int[] pos = bingsan.get(i);
                if (!visited[pos[0]][pos[1]]) {
                    divideCnt += 1;
                    bfs(pos[0], pos[1]);
                }
            }

            if (divideCnt >= 2) {
                System.out.println(year);
                return;
            }

            year += 1;

            List<int[]> removeList = new ArrayList<>();
            for (int[] pos : bingsan) {
                int count = 0;
                for (int i = 0; i < 4; i++) {
                    int curX = pos[0] + dx[i];
                    int curY = pos[1] + dy[i];

                    if (isInRange(curX, curY) && map[curX][curY] == 0) {
                        count += 1;
                    }
                }
                removeList.add(new int[]{pos[0], pos[1], count});
            }

            bingsan.clear();
            for (int[] removePos : removeList) {
                map[removePos[0]][removePos[1]] = Math.max(0, map[removePos[0]][removePos[1]] - removePos[2]);
                if (map[removePos[0]][removePos[1]] > 0) {
                    bingsan.add(new int[]{removePos[0], removePos[1]});
                }
            }
        }

        System.out.println(0);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int curX = cur[0] + dx[i];
                int curY = cur[1] + dy[i];

                if (isInRange(curX, curY) && map[curX][curY] != 0 && !visited[curX][curY]) {
                    queue.offer(new int[]{curX, curY});
                }
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

}
