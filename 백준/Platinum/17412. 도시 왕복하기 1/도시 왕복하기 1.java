import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, P;

    private static int[][] capacity;
    private static int[][] flow;
    private static int[] route;

    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        capacity = new int[N + 1][N + 1];
        flow = new int[N + 1][N + 1];
        route = new int[N + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s].add(e);
            graph[e].add(s);
            capacity[s][e] = 1;
        }

        int answer = networkFlow(1, 2);
        System.out.println(answer);
    }

    private static int networkFlow(int s, int e) {
        int maxFlow = 0;

        while (true) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(s);

            Arrays.fill(route, -1);

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int i = 0; i < graph[cur].size(); i++) {
                    int next = graph[cur].get(i);
                    if (capacity[cur][next] - flow[cur][next] > 0 && route[next] == -1) {
                        queue.offer(next);
                        route[next] = cur;
                        if (next == e) break;
                    }
                }
            }

            if (route[e] == -1) break;
            int f = Integer.MAX_VALUE;
            for (int i = e; i != s; i = route[i]) {
                f = Math.min(f, capacity[route[i]][i] - flow[route[i]][i]);
            }

            for (int i = e; i != s; i = route[i]) {
                flow[route[i]][i] += f;
                flow[i][route[i]] -= f;
            }
            maxFlow += f;
        }

        return maxFlow;
    }
}
