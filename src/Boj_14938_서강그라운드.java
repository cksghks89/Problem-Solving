/*
    Boj 14938. 서강그라운드
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_14938_서강그라운드 {
    static int n, m, r;
    static ArrayList<ArrayList<int[]>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, l});
            graph.get(b).add(new int[]{a, l});
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int[] curDist = dijkstra(i);
            int curItemCnt = getMaxItem(curDist, items);
            ans = Math.max(ans, curItemCnt);
        }

        System.out.println(ans);
    }

    static int getMaxItem(int[] dist, int[] items) {
        int rtn = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] != Integer.MAX_VALUE && dist[i] <= m) {
                rtn += items[i];
            }
        }
        return rtn;
    }

    static int[] dijkstra(int s) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        pq.offer(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int from = cur[0];

            for (int i = 0; i < graph.get(from).size(); i++) {
                int to = graph.get(from).get(i)[0];
                int weight = graph.get(from).get(i)[1];

                if (dist[to] > dist[from] + weight) {
                    dist[to] = dist[from] + weight;
                    pq.offer(new int[]{to, dist[to]});
                }
            }
        }

        return dist;
    }
}
