import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] sights;
    private static List<int[]>[] graph;
    private static boolean[] visited;

    private static long INF = 100000L * 100000 + 1L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sights[N - 1] = 0;
        visited = new boolean[N];

        graph = new List[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, t});
            graph[b].add(new int[]{a, t});
        }

        long answer = dijkstra(0);

        System.out.println(answer == INF ? -1 : answer);
    }

    private static long dijkstra(int x) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Math.toIntExact(o1[1] - o2[1]));
        pq.offer(new long[]{x, 0});

        long[] distance = new long[N];
        Arrays.fill(distance, INF);
        distance[x] = 0;

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            if (visited[(int)cur[0]]) continue;
            visited[(int)cur[0]] = true;

            for (int i = 0; i < graph[(int)cur[0]].size(); i++) {
                int[] next = graph[(int)cur[0]].get(i);

                if (sights[next[0]] == 0 && distance[next[0]] > cur[1] + next[1]) {
                    distance[next[0]] = cur[1] + next[1];
                    pq.offer(new long[]{next[0], distance[next[0]]});
                }
            }
        }

        return distance[N - 1];
    }
}
