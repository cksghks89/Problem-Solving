import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    private static int N;
    private static int d;
    private static char[][] friends;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        d = Integer.parseInt(br.readLine());

        friends = new char[N][N];

        for (int i = 0; i < N; i++) {
            friends[i] = br.readLine().toCharArray();
        }

        int answer = -1;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, bfs(i));
        }

        System.out.println(answer);
    }

    private static int bfs(int start) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, 0});

        boolean[] visited = new boolean[N];

        int max = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;
            max = Math.max(max, cur[1]);

            for (int i = 0; i < N; i++) {
                if (friends[cur[0]][i] == 'N') continue;
                if (visited[i]) continue;
                queue.offer(new int[]{i, cur[1] + 1});
            }
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                return -1;
            }
        }

        return max * d;
    }
}
