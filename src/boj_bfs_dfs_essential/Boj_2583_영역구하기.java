/*
    Boj 2583. 영역 구하기
    level. silver 1
    solved by 송찬환
 */
package boj_bfs_dfs_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_2583_영역구하기 {
    static int M;
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            int[] pos = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            fillBox(pos);
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    result.add(bfs(i, j));
                }
            }
        }
        Collections.sort(result);
        System.out.println(result.size());
        for(int area : result){
            System.out.print(area + " ");
        }
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        visited[x][y] = true;
        int area = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x1 = cur[0];
            int y1 = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    area += 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return area;
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < M) && (0 <= y && y < N);
    }

    static void fillBox(int[] pos) {
        int x1 = pos[0];
        int y1 = pos[1];
        int x2 = pos[2];
        int y2 = pos[3];

        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) {
                map[i][j] = 1;
            }
        }
    }
}
