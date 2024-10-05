import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K;
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
            graph[B].add(A);
        }

        for (int i = 1; i <= N; i++) {
            int[] dist = getDistance(i);

            for (int j = 1; j <= N; j++) {
                if (dist[j] > 6) {
                    System.out.println("Big World!");
                    return;
                }
            }
        }

        System.out.println("Small World!");
    }

    private static int[] getDistance(int idx) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{idx, 0});

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[idx] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int next = graph[cur[0]].get(i);

                if (cur[1] + 1 < dist[next]) {
                    dist[next] = cur[1] + 1;
                    pq.offer(new int[]{next, cur[1] + 1});
                }
            }
        }

        return dist;
    }
}
