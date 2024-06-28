import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        B = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        int answer = bfs();

        System.out.println(answer);
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 1, 0});    // 0: 시간, 1: s, 2: 개수

        boolean[][] visited = new boolean[25000][50000];

        int max = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == K) {
                max = Math.max(max, cur[2]);
                continue;
            }

            if (cur[2] >= 50000) {
                queue.offer(new int[]{cur[0] + 1, cur[1], cur[2] + cur[1]});
                continue;
            }

            if (visited[cur[1]][cur[2]]) continue;
            visited[cur[1]][cur[2]] = true;


            queue.offer(new int[]{cur[0] + 1, cur[1], cur[2] + cur[1]});
            for (int i = 0; i < N; i++) {
                if (cur[2] >= A[i]) {
                    queue.offer(new int[]{cur[0] + 1, cur[1] + B[i], cur[2] - A[i]});
                }
            }
        }

        return max;
    }
}
