import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int F, S, G, U, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int result = bfs();

        if (result == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(result);
        }
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{S, 0});

        boolean[] visited = new boolean[F + 1];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            if (cur[0] == G) {
                return cur[1];
            }

            int up = cur[0] + U;
            int down = cur[0] - D;

            if (isInRange(up) && !visited[up]) {
                queue.offer(new int[]{up, cur[1] + 1});
            }

            if (isInRange(down) && !visited[down]) {
                queue.offer(new int[]{down, cur[1] + 1});
            }
        }

        return -1;
    }

    private static boolean isInRange(int x) {
        return 1 <= x && x <= F;
    }
}
