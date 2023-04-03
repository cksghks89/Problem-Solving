import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static List<int[]>[] graph;
    static int INF = 11_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
        }

        int[][] dist = floydWarshall();

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if(i == j) continue;
                if (dist[i][j] != INF && dist[j][i] != INF) {
                    result = Math.min(result, dist[i][j] + dist[j][i]);
                }
            }
        }

        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    static int[][] floydWarshall() {
        int[][] dist = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
            for (int[] cur : graph[i]) {
                dist[i][cur[0]] = cur[1];
            }
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 1; k <= V; k++) {
                    if (j == k) continue;
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }

        return dist;
    }
}
