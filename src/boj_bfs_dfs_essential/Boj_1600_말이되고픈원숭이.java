/*
    Boj 1600. 말이 되고픈 원숭이
    level. gold 3
    solved by 송찬환
 */
package boj_bfs_dfs_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1600_말이되고픈원숭이 {
    static int K;
    static int W;
    static int H;

    static int[][] map;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int[] knightX = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] knightY = {2, 1, -1, -2, 2, 1, -1, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[K+1][H][W];

        queue.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int k = cur[2];
            int move = cur[3];

            if (x == H - 1 && y == W - 1) {
                return move;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == 0 && !visited[k][nx][ny]) {
                    visited[k][nx][ny] = true;
                    queue.offer(new int[]{nx, ny, k, move + 1});
                }
            }

            if (k < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + knightX[i];
                    int ny = y + knightY[i];

                    if (isInRange(nx, ny) && map[nx][ny] == 0 && !visited[k+1][nx][ny]) {
                        visited[k+1][nx][ny] = true;
                        queue.offer(new int[]{nx, ny, k + 1, move + 1});
                    }
                }
            }
        }
        return -1;
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < H) && (0 <= y && y < W);
    }
}
