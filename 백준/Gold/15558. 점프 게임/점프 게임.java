import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, k;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[2][N];

        for (int i = 0; i < 2; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0});

        boolean[][] visited = new boolean[2][N + k + 1];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (cur[1] >= N || cur[1] + k >= N || cur[1] + 1 >= N) {
                return 1;
            }

            if (board[cur[0]][cur[1] + 1] == 1) {
                queue.offer(new int[]{cur[0], cur[1] + 1, cur[2] + 1});
            }
            if (cur[1] - 1 >= 0 && board[cur[0]][cur[1] - 1] == 1 && cur[1] - 1 >= cur[2] + 1) {
                queue.offer(new int[]{cur[0], cur[1] - 1, cur[2] + 1});
            }
            if (board[cur[0] == 0 ? 1 : 0][cur[1] + k] == 1) {
                queue.offer(new int[]{cur[0] == 0 ? 1 : 0, cur[1] + k, cur[2] + 1});
            }
        }

        return 0;
    }
}
