/*
    Boj 1976. 여행 가자
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1976_여행가자 {
    static int N;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] travel = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(x -> x - 1)
                .toArray();

        int[] dist = dijkstra(travel[0]);

        String ans = "YES";
        for (int i = 1; i < travel.length; i++) {
            if (dist[travel[i]] == Integer.MAX_VALUE) {
                ans = "NO";
                break;
            }
        }

        System.out.println(ans);
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        pq.offer(new int[]{start, 0});

        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int i = 0; i < N; i++) {
                if (graph[cur[0]][i] == 1 && dist[i] > cur[1] + 1) {
                    dist[i] = cur[1] + 1;
                    pq.offer(new int[]{i, dist[i]});
                }
            }
        }

        return dist;
    }
}
