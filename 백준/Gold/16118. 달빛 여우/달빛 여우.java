import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static List<int[]>[] graph;
    private static long INF = -1;

    private static class Node {
        int v;
        long d;
        int boost;

        public Node(int v, long d) {
            this.v = v;
            this.d = d;
        }

        public Node(int v, long d, int boost) {
            this.v = v;
            this.d = d;
            this.boost = boost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, 2 * d});
            graph[b].add(new int[]{a, 2 * d});
        }

        long[] foxDist = foxDijkstra();
        long[] wolfDist = wolfDijkstra();

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (foxDist[i] != INF && foxDist[i] < wolfDist[i]) {
                cnt += 1;
            }
        }

        System.out.println(cnt);

    }

    private static long[] foxDijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.d, o2.d));
        pq.offer(new Node(1, 0));

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        boolean[] visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (int i = 0; i < graph[cur.v].size(); i++) {
                int[] next = graph[cur.v].get(i);

                if (dist[next[0]] > cur.d + next[1] || dist[next[0]] == INF) {
                    dist[next[0]] = cur.d + next[1];
                    pq.offer(new Node(next[0], dist[next[0]]));
                }
            }
        }

        return dist;
    }

    private static long[] wolfDijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.d, o2.d));
        pq.offer(new Node(1, 0, 1));

        long[][] dist = new long[N + 1][2];
        for (int i = 0; i <= N; i++) {
            dist[i][0] = INF;
            dist[i][1] = INF;
        }
        dist[1][1] = 0;

        boolean[][] visited = new boolean[N + 1][2];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.v][cur.boost]) continue;
            visited[cur.v][cur.boost] = true;

            for (int i = 0; i < graph[cur.v].size(); i++) {
                int[] next = graph[cur.v].get(i);

                if (cur.boost == 1) {
                    if (dist[next[0]][0] > cur.d + next[1] / 2 || dist[next[0]][0] == INF) {
                        dist[next[0]][0] = cur.d + next[1] / 2;
                        pq.offer(new Node(next[0], dist[next[0]][0], 0));
                    }
                } else if (cur.boost == 0) {
                    if (dist[next[0]][1] > cur.d + next[1] * 2L || dist[next[0]][1] == INF) {
                        dist[next[0]][1] = cur.d + next[1] * 2L;
                        pq.offer(new Node(next[0], dist[next[0]][1], 1));
                    }
                }
            }
        }

        long[] result = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            if (dist[i][0] != INF && dist[i][1] != INF) {
                result[i] = Math.min(dist[i][0], dist[i][1]);
            } else if (dist[i][0] == INF && dist[i][1] != INF) {
                result[i] = dist[i][1];
            } else if (dist[i][0] != INF && dist[i][1] == INF) {
                result[i] = dist[i][0];
            } else {
                result[i] = Integer.MAX_VALUE;
            }
        }

        return result;
    }
}
