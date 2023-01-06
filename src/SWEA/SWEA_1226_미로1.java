package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1226_미로1 {
    static final int SIZE = 16;

    static int[][] map;
    static int[] start;
    static int[] end;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();
            map = new int[SIZE][SIZE];

            for (int i = 0; i < SIZE; i++) {
                char[] line = br.readLine().toCharArray();
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = line[j] - '0';

                    if (map[i][j] == 2) {
                        start = new int[]{i, j};
                    } else if (map[i][j] == 3) {
                        end = new int[]{i, j};
                    }
                }
            }

            sb.append("#"+tc+" ").append(bfs()).append("\n");
        }
        System.out.print(sb.toString());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);

        boolean[][] visited = new boolean[SIZE][SIZE];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int x = cur[0];
            int y = cur[1];

            if (x == end[0] && y == end[1]) {
                return 1;
            }

            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != 1) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return 0;
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < SIZE) && (0 <= y && y < SIZE);
    }
}
