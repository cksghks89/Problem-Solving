/*
    Boj 17143. 낚시왕
    level. gold 1
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17143_낚시왕 {
    static class Shark {
        int s, d, z;

        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int R, C, M;
    static Shark[][] sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());   // 세로
        C = Integer.parseInt(st.nextToken());   // 가로
        M = Integer.parseInt(st.nextToken());   // 상어 수
        sharks = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharks[r][c] = new Shark(s, d, z);
        }

        int sum = 0;
        for (int i = 1; i <= C; i++) {
            for (int j = 1; j <= R; j++) {
                if (sharks[j][i] != null) {
                    sum += sharks[j][i].z;
                    sharks[j][i] = null;
                    break;
                }
            }

            move();
        }

        System.out.println(sum);
    }

    static void move() {
        Shark[][] newSharks = new Shark[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (sharks[i][j] != null) {
                    Shark cur = sharks[i][j];
                    move(i, j, cur.d, cur.s, newSharks, cur);
                }
            }
        }
        sharks = newSharks;
    }

    static void move(int x, int y, int d, int cnt, Shark[][] newSharks, Shark cur) {
        if (d == 1) {
            if (x - 1 < cnt) {
                move(1, y, 2, cnt - (x - 1), newSharks, cur);
                return;
            }
            if (newSharks[x - cnt][y] != null) {
                newSharks[x - cnt][y] = newSharks[x - cnt][y].z > cur.z ? newSharks[x - cnt][y] : new Shark(cur.s, 1, cur.z);
            } else {
                newSharks[x - cnt][y] = new Shark(cur.s, 1, cur.z);
            }
        } else if (d == 2) {
            if (R - x < cnt) {
                move(R, y, 1, cnt - (R - x), newSharks, cur);
                return;
            }
            if (newSharks[x + cnt][y] != null) {
                newSharks[x + cnt][y] = newSharks[x + cnt][y].z > cur.z ? newSharks[x + cnt][y] : new Shark(cur.s, 2, cur.z);
            } else {
                newSharks[x + cnt][y] = new Shark(cur.s, 2, cur.z);
            }
        } else if (d == 3) {
            if (C - y < cnt) {
                move(x, C, 4, cnt - (C - y), newSharks, cur);
                return;
            }
            if (newSharks[x][y + cnt] != null) {
                newSharks[x][y + cnt] = newSharks[x][y + cnt].z > cur.z ? newSharks[x][y + cnt] : new Shark(cur.s, 3, cur.z);
            } else {
                newSharks[x][y + cnt] = new Shark(cur.s, 3, cur.z);
            }
        } else if (d == 4) {
            if (y - 1 < cnt) {
                move(x, 1, 3, cnt - (y - 1), newSharks, cur);
                return;
            }
            if (newSharks[x][y - cnt] != null) {
                newSharks[x][y - cnt] = newSharks[x][y - cnt].z > cur.z ? newSharks[x][y - cnt] : new Shark(cur.s, 4, cur.z);
            } else {
                newSharks[x][y - cnt] = new Shark(cur.s, 4, cur.z);
            }
        }
    }
}
