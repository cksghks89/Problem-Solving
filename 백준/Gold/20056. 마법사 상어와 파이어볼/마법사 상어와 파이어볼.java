import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class FB {
        int r, c;
        int m;
        int d;
        int s;

        public FB(int r, int c, int m, int d, int s) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }

    private static int N, M, K;
    private static List<FB>[][] map;

    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        // 입출력 -- start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // N : 격자 크기
        M = Integer.parseInt(st.nextToken());   // M : 파이어볼 개수
        K = Integer.parseInt(st.nextToken());   // K : 명령 개수

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r - 1][c - 1].add(new FB(r - 1, c - 1, m, d, s));
        }
        // 입출력 -- end


        // 로직 -- start
        for (int i = 0; i < K; i++) {
            // 1. 이동
            move();

            // 2. 2개 이상의 파이어볼 합치기
            merge();
        }
        // 로직 -- end

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (FB cur : map[i][j]) {
                    answer += cur.m;
                }
            }
        }

        System.out.println(answer);
    }

    private static void merge() {
        List<FB>[][] newMap = new List[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].isEmpty()) {
                    continue;
                } else if (map[i][j].size() == 1) {
                    newMap[i][j].add(map[i][j].get(0));
                    continue;
                }

                int m = 0;
                int s = 0;
                int temp = map[i][j].get(0).d % 2;
                boolean d = true;
                for (FB cur : map[i][j]) {
                    m += cur.m;
                    s += cur.s;
                    if (cur.d % 2 != temp) {
                        d = false;
                    }
                }
                m /= 5;
                s /= map[i][j].size();

                if (m == 0) continue;

                // 방향 체크
                if (d) {
                    for (int k = 0; k <= 6; k += 2) {
                        FB insert = new FB(i, j, m, k, s);
                        newMap[i][j].add(insert);
                    }
                } else {
                    for (int k = 1; k <= 7; k += 2) {
                        FB insert = new FB(i, j, m, k, s);
                        newMap[i][j].add(insert);
                    }
                }
            }
        }

        map = newMap;
    }

    private static void move() {
        List<FB>[][] newMap = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].isEmpty()) continue;

                for (FB cur : map[i][j]) {
                    cur.r = (cur.r + dx[cur.d] * (cur.s % N) + N) % N;
                    cur.c = (cur.c + dy[cur.d] * (cur.s % N) + N) % N;
                    newMap[cur.r][cur.c].add(cur);
                }
            }
        }

        map = newMap;
    }
}
