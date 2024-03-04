import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static List<int[]>[] graph;
    private static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        int answer = dijkstra();
        System.out.println(answer);
    }

    private static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{1, 0});

        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, MAX);
        dist[1] = 0;


        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] nx = graph[cur[0]].get(i);

                if (dist[nx[0]] > cur[1] + nx[1]) {
                    dist[nx[0]] = cur[1] + nx[1];
                    pq.offer(new int[]{nx[0], dist[nx[0]]});
                }
            }
        }

        return dist[N];
    }
}
