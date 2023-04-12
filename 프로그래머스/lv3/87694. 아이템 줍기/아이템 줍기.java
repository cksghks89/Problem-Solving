import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[102][102];

        for (int i = 0; i < rectangle.length; i++) {
            for (int j = rectangle[i][0] * 2; j <= rectangle[i][2] * 2; j++) {
                for (int k = rectangle[i][1] * 2; k <= rectangle[i][3] * 2; k++) {
                    map[j][k] = 1;
                }
            }
        }

        for (int i = 0; i < rectangle.length; i++) {
            for (int j = rectangle[i][0] * 2 + 1; j < rectangle[i][2] * 2; j++) {
                for (int k = rectangle[i][1] * 2 + 1; k < rectangle[i][3] * 2; k++) {
                    map[j][k] = 0;
                }
            }
        }

        return getShortestPath(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }

    private int getShortestPath(int cX, int cY, int iX, int iY) {
        boolean[][] visited = new boolean[102][102];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{cX, cY, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == iX && cur[1] == iY) {
                return cur[2];
            }

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
        return -1;
    }

    private boolean isInRange(int x, int y) {
        return 0 <= x && x < 102 && 0 <= y && y < 102;
    }
}