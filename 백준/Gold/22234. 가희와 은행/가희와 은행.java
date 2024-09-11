import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, T, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int px = Integer.parseInt(st.nextToken());
            int tx = Integer.parseInt(st.nextToken());

            queue.offer(new int[]{px, tx});
        }

        int M = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int px = Integer.parseInt(st.nextToken());
            int tx = Integer.parseInt(st.nextToken());
            int cx = Integer.parseInt(st.nextToken());

            pq.offer(new int[]{px, tx, cx});
        }

        StringBuilder answer = new StringBuilder();
        int count = 0;
        int time = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int workTime = Math.min(cur[1], T);
            for (int i = 0; i < workTime; i++) {
                answer.append(cur[0]).append('\n');
                count += 1;
                time += 1;
                cur[1] -= 1;

                if (count == W) {
                    System.out.println(answer);
                    return;
                }
            }

            while (!pq.isEmpty() && pq.peek()[2] <= time) {
                queue.offer(pq.poll());
            }

            if (cur[1] > 0) {
                queue.offer(cur);
            }
        }
    }
}
