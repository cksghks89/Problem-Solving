import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] A;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N - 2; i++) {
            for (int j = 2; j <= N - 1; j++) {
                divide(i, j);
            }
        }

        System.out.println(answer);
    }

    private static void divide(int x, int y) {
        for (int d1 = 1; d1 <= N; d1++) {
            for (int d2 = 1; d2 <= N; d2++) {
                if (!isPossible(x, y, d1, d2)) continue;

                int[][] grid = new int[N + 1][N + 1];

                // 1번 선거구
                for (int i = 1; i < x + d1; i++) for (int j = 1; j <= y; j++) grid[i][j] = 1;
                // 2번 선거구
                for (int i = 1; i <= x + d2; i++) for (int j = y + 1; j <= N; j++) grid[i][j] = 2;
                // 3번 선거구
                for (int i = x + d1; i <= N; i++) for (int j = 1; j < y - d1 + d2; j++) grid[i][j] = 3;
                // 4번 선거구
                for (int i = x + d2 + 1; i <= N; i++) for (int j = y - d1 + d2; j <= N; j++) grid[i][j] = 4;

                // 5번 선거구
                for (int i = 0; i <= d1; i++) {
                    grid[x + i][y - i] = 5;
                    grid[x + d2 + i][y + d2 - i] = 5;
                }
                for (int i = 0; i <= d2; i++) {
                    grid[x + i][y + i] = 5;
                    grid[x + d1 + i][y - d1 + i] = 5;
                }

                // 3. bfs 돌면서 개수 세기
                boolean[][] visited = new boolean[N + 1][N + 1];
                List<Integer> sumList = new ArrayList<>();

                sumList.add(bfs(grid, 1, 1, 1, visited));
                sumList.add(bfs(grid, 1, N, 2, visited));
                sumList.add(bfs(grid, N, 1, 3, visited));
                sumList.add(bfs(grid, N, N, 4, visited));

                int fiveSum = 0;
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (visited[i][j]) continue;
                        fiveSum += A[i][j];
                    }
                }

                sumList.add(fiveSum);

                sumList.sort((o1, o2) -> o1 - o2);
                answer = Math.min(answer, sumList.get(4) - sumList.get(0));
            }
        }
    }

    private static int bfs(int[][] grid, int x, int y, int key, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        int sum = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            sum += A[cur[0]][cur[1]];

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && grid[nx][ny] == key && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return sum;
    }

    private static boolean isPossible(int x, int y, int d1, int d2) {
        return (1 <= x && x + d1 + d2 <= N) && (1 <= y - d1 && y + d2 <= N);
    }

    private static boolean isInRange(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= N;
    }
}
