import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m;
    private static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int[][] dist = dijkstra(i);

            for (int j = 1; j <= n; j++) {
                if (dist[j][1] == 0) {
                    sb.append('-').append(' ');
                } else {
                    sb.append(dist[j][1]).append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int[][] dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{start, 0, 0});
        int[][] dist = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            dist[i][0] = 11_000_000;
        }
        dist[start][0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);

                if (cur[1] + next[1] < dist[next[0]][0]) {
                    dist[next[0]][0] = cur[1] + next[1];
                    if (cur[2] == 0) {
                        dist[next[0]][1] = next[0];
                    } else {
                        dist[next[0]][1] = cur[2];
                    }
                    pq.offer(new int[]{next[0], dist[next[0]][0], dist[next[0]][1]});
                }
            }
        }

        return dist;
    }
}
