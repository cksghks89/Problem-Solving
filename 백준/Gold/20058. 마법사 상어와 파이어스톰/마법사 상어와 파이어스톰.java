import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, Q;
    private static int[][] fields;

    private static boolean[][] visited;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = 1 << Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        fields = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            // 1. 부분 격자 회전
            lOperation(L);

            // 2. 인접 칸 세고 얼음 줄이기
            countingAndRemoveIce();
        }

        // 3. 가장 큰 덩어리 찾기
        int sum = 0;
        int biggestCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += fields[i][j];
                if (visited[i][j] || fields[i][j] == 0) continue;
                biggestCount = Math.max(biggestCount, getBiggestIce(i, j));
            }
        }

        System.out.println(sum + "\n" + biggestCount);
    }

    private static int getBiggestIce(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        int count = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            count += 1;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && fields[nx][ny] != 0 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return count;
    }

    private static void countingAndRemoveIce() {
        List<int[]> removeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (isInRange(nx, ny) && fields[nx][ny] > 0) {
                        count += 1;
                    }
                }

                if (count < 3) {
                    removeList.add(new int[]{i, j});
                }
            }
        }

        for (int[] remove : removeList) {
            fields[remove[0]][remove[1]] = Math.max(fields[remove[0]][remove[1]] - 1, 0);
        }
    }

    private static void lOperation(int L) {
        int width = 1 << L;

        for (int i = 0; i < N; i += width) {
            for (int j = 0; j < N; j += width) {
                rotate(i, j, width);
            }
        }
    }

    private static void rotate(int x, int y, int width) {
        int[][] copy = new int[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                copy[j][width - 1 - i] = fields[x + i][y + j];
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                fields[x + i][y + j] = copy[i][j];
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
