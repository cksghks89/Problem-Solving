import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, D;
    private static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        graph = new List[10001];
        for (int i = 0; i <= 10000; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph[s].add(new int[]{d, l});
        }

        int answer = dijkstra();

        System.out.println(answer);
    }

    private static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{0, 0});

        int[] dist = new int[10001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        int result = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == D) {
                result = Math.min(result, cur[1]);
            }

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);
                if (cur[1] + next[1] < dist[next[0]]) {
                    dist[next[0]] = cur[1] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }

            if (cur[0] + 1 <= D && cur[1] + 1 < dist[cur[0] + 1]) {
                dist[cur[0] + 1] = cur[1] + 1;
                pq.offer(new int[]{cur[0] + 1, dist[cur[0] + 1]});
            }
        }

        return result;
    }
}
