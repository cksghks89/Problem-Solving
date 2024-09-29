import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static long L;
    private static int N, K;
    private static Queue<long[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Long.parseLong(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        queue = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            queue.offer(new long[]{Long.parseLong(st.nextToken()), 0L});
        }

        StringBuilder answer = new StringBuilder();

        bfs(K, answer);

        System.out.println(answer);
    }

    private static void bfs(int K, StringBuilder answer) {
        Set<Long> visited = new HashSet<>();

        int count = 0;
        while (!queue.isEmpty()) {
            long[] cur = queue.poll();

            if (visited.contains(cur[0])) continue;
            visited.add(cur[0]);
            answer.append(cur[1]).append('\n');
            count += 1;

            if (count == K) break;

            if (0 <= cur[0] - 1 && !visited.contains(cur[0] - 1)) {
                queue.offer(new long[]{cur[0] - 1, cur[1] + 1});
            }
            if (cur[0] + 1 <= L && !visited.contains(cur[0] + 1)) {
                queue.offer(new long[]{cur[0] + 1, cur[1] + 1});
            }
        }
    }
}
