/*
    Boj 1865. 웜홀
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1865_웜홀 {
    static int N, M, W;
    static ArrayList<Edge> edges;
    static boolean[] visited;

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            visited = new boolean[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                edges.add(new Edge(s, e, -t));
            }

            boolean result = false;

            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    result = bellmanFord(i);
                    if (result) {
                        break;
                    }
                }
            }

            if (result) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean bellmanFord(int start) {
        int[] dist = new int[N + 1];
        boolean isCycle = false;

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < edges.size(); j++) {
                Edge cur = edges.get(j);

                if (dist[cur.from] == Integer.MAX_VALUE) {
                    continue;
                }

                if (dist[cur.to] > dist[cur.from] + cur.weight) {
                    dist[cur.to] = dist[cur.from] + cur.weight;
                    visited[cur.to] = true;
                    if (i == N) {
                        isCycle = true;
                    }
                }
            }
        }

        return isCycle;
    }
}
