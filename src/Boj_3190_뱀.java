/*
    Boj 3190. 뱀
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_3190_뱀 {
    static int N;
    static int[][] map;

    static Queue<List<Integer>> changeDirection = new LinkedList<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x - 1][y - 1] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            String C = st.nextToken();

            if (C.equals("D")) {
                changeDirection.offer(List.of(X, 3));
            } else if (C.equals("L")) {
                changeDirection.offer(List.of(X, 1));
            }
        }
        map[0][0] = 9;

        dummy(0, 0, 0, 0, 3, 0, changeDirection.poll());
        System.out.println(count + 1);
    }

    static void dummy(int x, int y, int tx, int ty, int d, int cnt, List<Integer> change) {
        if (change != null && cnt == change.get(0)) {
            d = (d + change.get(1)) % 4;
            change = changeDirection.poll();
        }

        // 다음 갈 곳이 범위 밖 or 몸
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (!isInRange(nx, ny)) {
            count = cnt;
            return;
        } else if (map[nx][ny] >= 9) {
            count = cnt;
            return;
        }

        if (map[nx][ny] == 1) {
            map[nx][ny] = cnt + 10;
            dummy(nx, ny, tx, ty, d, cnt + 1, change);
        } else if (map[nx][ny] == 0) {
            map[nx][ny] = cnt + 10;
            int tailCnt = map[tx][ty];

            map[tx][ty] = 0;
            for (int i = 0; i < 4; i++) {
                if (isInRange(tx + dx[i], ty + dy[i])) {
                    if (map[tx + dx[i]][ty + dy[i]] == tailCnt + 1) {
                        tx = tx + dx[i];
                        ty = ty + dy[i];
                        break;
                    }
                }
            }

            dummy(nx, ny, tx, ty, d, cnt + 1, change);
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }
}
