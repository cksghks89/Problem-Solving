import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, Q;
    private static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            graph[p].add(new int[]{q, r});
            graph[q].add(new int[]{p, r});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int answer = getCount(v, k);
            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }

    private static int getCount(int idx, int k) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[idx] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{idx, Integer.MAX_VALUE});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);

                if (dist[next[0]] >= 0) continue;
                dist[next[0]] = Math.min(cur[1], next[1]);
                queue.offer(new int[]{next[0], dist[next[0]]});
            }
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] >= k) count += 1;
        }

        return count;
    }
}
