import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int S = 'A', E = 'Z';

    private static int N;
    private static int[][] capacity, flow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        capacity = new int[200][200];
        flow = new int[200][200];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = st.nextToken().charAt(0) - 'A';
            int b = st.nextToken().charAt(0) - 'A';
            int c = Integer.parseInt(st.nextToken());

            capacity[a][b] += c;
            capacity[b][a] += c;
        }

        int totalFlow = networkFlow(0, 'Z' - 'A');
        System.out.println(totalFlow);
    }

    private static int networkFlow(int source, int sink) {
        for (int i = 0; i < 200; i++) {
            Arrays.fill(flow[i], 0);
        }

        int answer = 0;

        while (true) {
            int[] parent = new int[200];

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(source);

            Arrays.fill(parent, -1);
            parent[source] = source;

            while (!queue.isEmpty() && parent[sink] == -1) {
                int cur = queue.poll();
                for (int i = 0; i < 200; i++) {
                    if (capacity[cur][i] - flow[cur][i] > 0 && parent[i] == -1) {
                        queue.offer(i);
                        parent[i] = cur;
                    }
                }
            }

            if (parent[sink] == -1) break;

            int amount = 11_000_000;
            for (int i = sink; i != source; i = parent[i]) {
                amount = Math.min(capacity[parent[i]][i] - flow[parent[i]][i], amount);
            }

            for (int i = sink; i != source; i = parent[i]) {
                flow[parent[i]][i] += amount;
                flow[i][parent[i]] -= amount;
            }

            answer += amount;
        }

        return answer;
    }
}
