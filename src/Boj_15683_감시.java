/*
    Boj 15683. 감시
    level. gold 4
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_15683_감시 {
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> cctv;
    static int[] cctvDirectCnt = {0, 4, 2, 4, 4, 1};
    static int[] out;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctv = new ArrayList<>();

        int wall = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 5) cctv.add(new int[]{i, j});
                if (map[i][j] == 6) wall++;
            }
        }

        out = new int[cctv.size()];
        min = Integer.MAX_VALUE;

        bruteforce(0);
        System.out.println(min);
    }

    static void bruteforce(int cnt) {
        if (cnt == cctv.size()) {
            min = Math.min(min, search());
            return;
        }

        int[] cur = cctv.get(cnt);
        for (int i = 0; i < cctvDirectCnt[map[cur[0]][cur[1]]]; i++) {
            out[cnt] = i;
            bruteforce(cnt + 1);
        }
    }

    static int search() {
        int[][] curMap = new int[N][M];

        for (int i = 0; i < cctv.size(); i++) {
            int[] cur = cctv.get(i);
            int d = out[i];

            switch (map[cur[0]][cur[1]]) {
                case 1:
                    watch(cur[0], cur[1], d, curMap);
                    break;
                case 2:
                    watch(cur[0], cur[1], d, curMap);
                    watch(cur[0], cur[1], (d + 2) % 4, curMap);
                    break;
                case 3:
                    watch(cur[0], cur[1], d, curMap);
                    watch(cur[0], cur[1], (d + 1) % 4, curMap);
                    break;
                case 4:
                    watch(cur[0], cur[1], d, curMap);
                    watch(cur[0], cur[1], (d + 1) % 4, curMap);
                    watch(cur[0], cur[1], (d + 2) % 4, curMap);
                    break;
                case 5:
                    watch(cur[0], cur[1], d, curMap);
                    watch(cur[0], cur[1], (d + 1) % 4, curMap);
                    watch(cur[0], cur[1], (d + 2) % 4, curMap);
                    watch(cur[0], cur[1], (d + 3) % 4, curMap);
                    break;
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (curMap[i][j] == 0 && map[i][j] != 6) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void watch(int x, int y, int d, int[][] curMap) {
        int nx = x;
        int ny = y;

        while (isInRange(nx, ny) && map[nx][ny] != 6) {
            curMap[nx][ny] = 1;
            nx += dx[d];
            ny += dy[d];
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < M);
    }
}
