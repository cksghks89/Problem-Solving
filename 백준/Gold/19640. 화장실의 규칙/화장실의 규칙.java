import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<int[]>[] queueArr = new Queue[M];
        for (int i = 0; i < M; i++) queueArr[i] = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            // 0: 순서, 1: 줄, 2: 근무일수, 2: 급한정도
            queueArr[i % M].offer(new int[]{i, i % M, d, h});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] != o2[2]) return o2[2] - o1[2];
            else if (o1[3] != o2[3]) return o2[3] - o1[3];
            else return o1[1] - o2[1];
        });

        // 초기 선두 세팅
        for (int i = 0; i < M; i++) {
            if (queueArr[i].isEmpty()) continue;
            pq.offer(queueArr[i].poll());
        }

        int count = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == K) break;
            count += 1;

            if (queueArr[cur[1]].isEmpty()) continue;
            pq.offer(queueArr[cur[1]].poll());
        }

        System.out.println(count);
    }
}
