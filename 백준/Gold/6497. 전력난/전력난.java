import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static List<int[]>[] graph;
    private static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) break;

            graph = new List[m];
            for (int i = 0; i < m; i++) graph[i] = new ArrayList<>();

            int total = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                graph[x].add(new int[]{y, z});
                graph[y].add(new int[]{x, z});
                total += z;
            }

            int answer = mst();
            sb.append(total - answer).append('\n');
        }

        System.out.println(sb);
    }

    private static int mst() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{0, 0});

        boolean[] visited = new boolean[n];

        int sum = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            sum += cur[1];

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] nx = graph[cur[0]].get(i);
                pq.offer(new int[]{nx[0], nx[1]});
            }
        }
        return sum;
    }
}
