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

        capacity = new int[2 * N + 1][2 * N + 1];
        flow = new int[2 * N + 1][2 * N + 1];
        route = new int[2 * N + 1];
        graph = new List[2 * N + 1];
        for (int i = 1; i <= 2 * N; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            capacity[i][N + i] = 1;
            graph[i].add(N + i);
            graph[N + i].add(i);
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[N + s].add(e);
            graph[e].add(N + s);
            graph[N + e].add(s);
            graph[s].add(N + e);
            capacity[N + s][e] = 1;
            capacity[N + e][s] = 1;
        }

        int answer = networkFlow(N + 1, 2);
        System.out.println(answer);
    }

    private static int networkFlow(int s, int e) {
        int result = 0;
        while (true) {
            Arrays.fill(route, -1);
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(s);

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
            result += f;
        }
        return result;
    }
}
