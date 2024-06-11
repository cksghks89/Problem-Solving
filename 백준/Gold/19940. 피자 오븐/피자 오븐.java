import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static int T;
    private static int[] dt = {60, 10, -10, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int cur = Integer.parseInt(br.readLine());
            int[] answer = bfs(cur);

            for (int j = 2; j < answer.length; j++) {
                sb.append(answer[j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int[] bfs(int target) {
        int count = target / 60;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            for (int i = 1; i < o1.length; i++) {
                if (o1[i] != o2[i]) return o1[i] - o2[i];
            }
            return 0;
        });

        pq.offer(new int[]{count * 60, count, count, 0, 0, 0, 0});

        int base = count * 60;
        boolean[] visited = new boolean[61];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] < base || cur[0] > base + 60) continue;

            if (visited[cur[0] - base]) continue;
            visited[cur[0] - base] = true;

            if (cur[0] == target) {
                return cur;
            }

            for (int i = 4; i >= 0; i--) {
                int nextId = cur[0] + dt[i];
                int[] next = new int[7];
                next[0] = Math.max(0, nextId);
                next[1] = cur[1] + 1;
                next[2] = cur[2];
                next[3] = cur[3];
                next[4] = cur[4];
                next[5] = cur[5];
                next[6] = cur[6];
                next[i + 2] += 1;
                pq.offer(next);
            }
        }

        return null;
    }

}
