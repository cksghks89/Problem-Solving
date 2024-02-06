import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] time;
    private static List<Integer>[] graph;
    private static int[] in;

    private static final int INF = 11_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        in = new int[N + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            in[i] = cnt;
            time[i] = t;

            for (int j = 0; j < cnt; j++) {
                int cur = Integer.parseInt(st.nextToken());
                graph[cur].add(i);
            }
        }

        int[] result = topologySort();

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, result[i]);
        }

        System.out.println(answer);
    }

    private static int[] topologySort() {
        int[] result = new int[N + 1];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                queue.offer(i);
                result[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                result[next] = Math.max(result[next], result[cur] + time[next]);

                in[next] -= 1;
                if (in[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return result;
    }
}
