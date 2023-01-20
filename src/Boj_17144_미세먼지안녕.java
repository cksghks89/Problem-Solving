/*
    Boj 17144. 미세먼지 안녕
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17144_미세먼지안녕 {
    static int R, C, T;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            spreadDust();
            cleanDust();
        }

        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans += map[i][j];
            }
        }

        System.out.println(ans + 2);
    }

    static void cleanDust() {
        int x = 0;

        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                x = i;
                break;
            }
        }

        // 위쪽 공기순환
        for (int i = x - 1; i >= 1; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < x; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i >= 1; i--) {
            map[x][i] = map[x][i - 1];
        }
        map[x][1] = 0;

        // 아래쪽 공기순환
        x += 1;
        for (int i = x + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        for (int i = 0; i < R - x; i++) {
            map[R - i - 1][C - 1] = map[R - i - 2][C - 1];
        }
        for (int i = C - 1; i >= 1; i--) {
            map[x][i] = map[x][i - 1];
        }
        map[x][1] = 0;
    }

    static void spreadDust() {
        int[][] nextMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    nextMap[i][j] = -1;
                    continue;
                }
                if (map[i][j] / 5 == 0) {
                    nextMap[i][j] += map[i][j];
                    continue;
                }

                int curDust = map[i][j];
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (isInRange(nx, ny)) {
                        nextMap[nx][ny] += curDust / 5;
                        cnt++;
                    }
                }
                nextMap[i][j] += curDust - cnt * (curDust / 5);
            }
        }
        map = nextMap;
    }

    static boolean isInRange(int x, int y) {
        if (!((0 <= x && x < R) && (0 <= y && y < C))) {
            return false;
        }
        return map[x][y] != -1;
    }
}
