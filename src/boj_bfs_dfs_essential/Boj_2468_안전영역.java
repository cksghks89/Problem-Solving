/*
    Boj 2468. 안전 영역
    level. silver 1
    solved by 송찬환
 */
package boj_bfs_dfs_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2468_안전영역 {
    static int[][] map;
    static boolean[][] visited;

    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int safeAreas = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];


        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur;
                max = Math.max(cur, max);
            }
        }

        for (int i = 1; i < max; i++) {
            visited = new boolean[N][N];
            int count = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if(!visited[j][k] && map[j][k] > i){
                        count++;
                        bfs(j, k, i);
                    }
                }
            }
            safeAreas = Math.max(count, safeAreas);
        }

        System.out.println(safeAreas);
    }

    static void bfs(int x, int y, int rain) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] > rain) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }
}
