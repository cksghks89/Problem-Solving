/*
    Boj 1504. 특정한 최단 경로
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

public class Boj_1504_특정한최단경로 {
    static int N, E;
    static int v1, v2;
    static ArrayList<ArrayList<int[]>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph.get(input[0]).add(new int[]{input[1], input[2]});
            graph.get(input[1]).add(new int[]{input[0], input[2]});
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int[] distOne = dijkstra(1);
        int[] distV1 = dijkstra(v1);
        int[] distV2 = dijkstra(v2);

        int result = Integer.MAX_VALUE;
        // 1 -> v1 -> v2 -> N
        if (!(distOne[v1] == Integer.MAX_VALUE || distV1[v2] == Integer.MAX_VALUE || distV2[N] == Integer.MAX_VALUE)) {
            result = distOne[v1] + distV1[v2] + distV2[N];
        }

        // 1 -> v2 -> v1 -> N
        if (!(distOne[v2] == Integer.MAX_VALUE || distV2[v1] == Integer.MAX_VALUE || distV1[N] == Integer.MAX_VALUE)) {
            result = Math.min(result, distOne[v2] + distV2[v1] + distV1[N]);
        }

        result = result == Integer.MAX_VALUE ? -1 : result;
        System.out.println(result);
    }

    static int[] dijkstra(int s) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        pq.add(new int[]{s, 0});

        dist[s] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int i = 0; i < graph.get(cur[0]).size(); i++) {
                int to = graph.get(cur[0]).get(i)[0];
                int weight = graph.get(cur[0]).get(i)[1];

                if (dist[to] > cur[1] + weight) {
                    dist[to] = cur[1] + weight;
                    pq.offer(new int[]{to, dist[to]});
                }
            }
        }

        return dist;
    }
}
