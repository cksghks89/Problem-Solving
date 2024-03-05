import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, T;
    private static int[][] arr;
    private static boolean[][] visited;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 1. 회전
            for (int j = x; j <= N; j += x) {
                spin(j, d, k);
            }

            // 2. 원판의 인접수 찾기 & 지우기 (BFS)
            visited = new boolean[N + 1][M];
            boolean isRemoved = false;
            for (int j = 1; j <= N; j++) {
                for (int l = 0; l < M; l++) {
                    if (arr[j][l] == -1) continue;
                    boolean adjacentAndRemove = findAdjacentAndRemove(j, l);
                    if (adjacentAndRemove) isRemoved = true;
                }
            }

            // 3. 인접수 없을 경우 모든 수 조정
            if (!isRemoved) {
                int sum = 0;
                int cnt = 0;
                for (int j = 1; j <= N; j++) {
                    for (int l = 0; l < M; l++) {
                        if (arr[j][l] != -1) {
                            sum += arr[j][l];
                            cnt += 1;
                        }
                    }
                }

                if (cnt != 0) {
                    double avg = 1.0 * sum / cnt;
                    for (int j = 1; j <= N; j++) {
                        for (int l = 0; l < M; l++) {
                            if (arr[j][l] != -1) {
                                if (arr[j][l] > avg) arr[j][l] -= 1;
                                else if (arr[j][l] < avg) arr[j][l] += 1;
                            }
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int j = 1; j <= N; j++) {
            for (int l = 0; l < M; l++) {
                if (arr[j][l] != -1) {
                    answer += arr[j][l];
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean findAdjacentAndRemove(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        // 아래 => x - 2 % N + 1
        // 위 => x + 2 % N - 1
        // 왼쪽, 오른쪽 => y - 1 + M % M, y + 1 % M
        List<int[]> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            list.add(cur);

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = (cur[1] + dy[i] + M) % M;

                if (isInRange(nx, ny) && !visited[nx][ny] && arr[nx][ny] == arr[cur[0]][cur[1]]) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        if (list.size() >= 2) {
            for (int[] cur : list) {
                arr[cur[0]][cur[1]] = -1;
            }
            return true;
        }
        return false;
    }

    private static void spin(int x, int d, int k) {
        if (d == 1) k = (-k + M) % M;

        int[] newArr = new int[M];

        for (int i = 0; i < M; i++) {
            newArr[(i + k) % M] = arr[x][i];
        }
        arr[x] = newArr;
    }

    private static boolean isInRange(int x, int y) {
        return 1 <= x && x <= N && 0 <= y && y < M;
    }
}
