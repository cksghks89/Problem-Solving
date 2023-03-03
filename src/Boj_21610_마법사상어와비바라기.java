/*
    Boj 21610. 마법사 상어와 비바라기
    level. gold 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_21610_마법사상어와비바라기 {
    static int N, M;
    static int[][] map;
    static List<int[]> cloud;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        cloud = new ArrayList<>();
        cloud.add(new int[]{N - 1, 0});
        cloud.add(new int[]{N - 1, 1});
        cloud.add(new int[]{N - 2, 0});
        cloud.add(new int[]{N - 2, 1});

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // 1. 구름이 d 방향으로 s 만큼 이동한다
            move(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물이 1 증가한다
            rain();

            // 4. 물이 증가한 칸에 물복사 버그를 시전한다
            copyWater();

            // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고 물의 양이 2 줄어든다.
            setCloud();
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += map[i][j];
            }
        }
        System.out.println(result);
    }

    private static List<int[]> copyCloud() {
        List<int[]> copy = new ArrayList<>();
        for (int[] cur : cloud) {
            copy.add(new int[]{cur[0], cur[1], map[cur[0]][cur[1]]});
            map[cur[0]][cur[1]] = 0;
        }
        return copy;
    }

    private static void setCloud() {
        List<int[]> copy = copyCloud();
        cloud.clear();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2) {
                    map[i][j] -= 2;
                    cloud.add(new int[]{i, j});
                }
            }
        }

        for (int[] cur : copy) {
            map[cur[0]][cur[1]] = cur[2];
        }
    }

    static int[] ddx = {-1, -1, 1, 1};
    static int[] ddy = {-1, 1, -1, 1};

    private static void copyWater() {
        int[][] newMap = new int[N][N];

        for (int[] cur : cloud) {
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + ddx[i];
                int ny = cur[1] + ddy[i];

                if (isInRange(nx, ny) && map[nx][ny] > 0) {
                    cnt += 1;
                }
            }
            newMap[cur[0]][cur[1]] = cnt;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] += map[i][j];
            }
        }
        map = newMap;
    }

    private static void rain() {
        for (int[] cur : cloud) {
            map[cur[0]][cur[1]] += 1;
        }
    }

    private static void move(int d, int s) {
        for (int[] cur : cloud) {
            cur[0] += dx[d] * s;
            cur[1] += dy[d] * s;

            while (cur[0] < 0) cur[0] += N;
            while (cur[0] >= N) cur[0] -= N;
            while (cur[1] < 0) cur[1] += N;
            while (cur[1] >= N) cur[1] -= N;
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }
}
