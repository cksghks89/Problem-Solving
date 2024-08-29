import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] scv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        scv = new int[N];
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        if (N == 1) {
            System.out.println((scv[0] - 1) / 9 + 1);
            return;
        } else if (N == 2) {
            answer = bfsTwo();
        } else {
            answer = bfsThree();
        }

        System.out.println(answer);
    }

    private static int bfsTwo() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{scv[0], scv[1], 0});

        boolean[][] visited = new boolean[61][61];

        int[][] minus = {
                {9, 3},
                {3, 9},
        };

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (cur[0] == 0 && cur[1] == 0) {
                return cur[2];
            }

            for (int i = 0; i < 2; i++) {
                int ma = Math.max(0, cur[0] - minus[0][i]);
                int mb = Math.max(0, cur[1] - minus[1][i]);

                queue.offer(new int[]{ma, mb, cur[2] + 1});
            }
        }

        return 0;
    }

    private static int bfsThree() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{scv[0], scv[1], scv[2], 0});

        boolean[][][] visited = new boolean[61][61][61];

        int[][] minus = {
                {9, 9, 3, 3, 1, 1},
                {3, 1, 9, 1, 9, 3},
                {1, 3, 1, 9, 3, 9}
        };

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]][cur[2]]) continue;
            visited[cur[0]][cur[1]][cur[2]] = true;

            if (cur[0] == 0 && cur[1] == 0 && cur[2] == 0) {
                return cur[3];
            }

            // 931, 913, 391, 319, 193, 139
            for (int i = 0; i < 6; i++) {
                int ma = Math.max(0, cur[0] - minus[0][i]);
                int mb = Math.max(0, cur[1] - minus[1][i]);
                int mc = Math.max(0, cur[2] - minus[2][i]);

                queue.offer(new int[]{ma, mb, mc, cur[3] + 1});
            }
        }

        return 0;
    }


}
