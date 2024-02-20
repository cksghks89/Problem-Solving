import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][][] initMap = new int[4][4][2];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int v = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                initMap[i][j][0] = v;
                initMap[i][j][1] = d - 1;
            }
        }

        int temp = initMap[0][0][0];
        initMap[0][0][0] = -2;
        int answer = recur(new int[]{0, 0}, initMap[0][0][1], initMap, temp);
        System.out.println(answer);
    }

    private static int recur(int[] shark, int d, int[][][] curMap, int sum) {
        // map 좌표값 0 인덱스 => -1 : 빈칸, -2 : 상어, 1 ~ 16 : 물고기
        // map 좌표값 1 인덱스 => 방향

        // 물고기 이동
        moveFish(curMap);

        // 상어 이동
        int nx = shark[0];
        int ny = shark[1];

        int max = 0;
        int cnt = 0;
        while (isInRange(nx, ny)) {
            nx += dx[d];
            ny += dy[d];

            if (isInRange(nx, ny) && curMap[nx][ny][0] != -1) {
                cnt += 1;
                int[][][] copy = copyMap(curMap);
                copy[nx][ny][0] = -2;
                copy[shark[0]][shark[1]][0] = -1;
                max = Math.max(max, recur(new int[]{nx, ny}, curMap[nx][ny][1], copy, sum + curMap[nx][ny][0]));
            }
        }

        if (cnt == 0) return sum;
        else return max;
    }

    private static void moveFish(int[][][] map) {
        int id = 1;
        while (id <= 16) {
            int[] cur = null;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (map[i][j][0] == id) {
                        cur = new int[]{map[i][j][0], map[i][j][1], i, j};
                    }
                }
            }
            id += 1;
            if (cur == null) continue;

            int cnt = 0;
            while (cnt != 8) {
                int nx = cur[2] + dx[(cur[1] + cnt) % 8];
                int ny = cur[3] + dy[(cur[1] + cnt) % 8];

                map[cur[2]][cur[3]][1] = (cur[1] + cnt) % 8;
                cnt += 1;

                if (isFishMoved(nx, ny, map)) {
                    int[] temp = map[cur[2]][cur[3]];
                    map[cur[2]][cur[3]] = map[nx][ny];
                    map[nx][ny] = temp;
                    break;
                }
            }
        }
    }

    private static int[][][] copyMap(int[][][] map) {
        int[][][] copy = new int[4][4][2];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy[i][j][0] = map[i][j][0];
                copy[i][j][1] = map[i][j][1];
            }
        }

        return copy;
    }

    private static boolean isFishMoved(int x, int y, int[][][] map) {
        return 0 <= x && x < 4 && 0 <= y && y < 4 && map[x][y][0] != -2;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
}
