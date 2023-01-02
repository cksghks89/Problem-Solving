/*
    Boj 1167_트리의 지름
    level. gold 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1167_트리의지름 {
    static int V;
    static ArrayList<int[]>[] graph;

    static int maxIdx;
    static int maxCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int idx = Integer.parseInt(st.nextToken());
                if (idx == -1) {
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());

                graph[id].add(new int[]{idx, weight});
            }
        }

        maxCost = Integer.MIN_VALUE;
        bfs(1);

        maxCost = Integer.MIN_VALUE;
        bfs(maxIdx);

        System.out.println(maxCost);

    }

    static void bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});

        boolean[] visited = new boolean[V + 1];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int idx = cur[0];
            int cost = cur[1];

            if (cost > maxCost) {
                maxCost = cost;
                maxIdx = idx;
            }

            if (visited[idx]) {
                continue;
            }
            visited[idx] = true;

            for (int i = 0; i < graph[idx].size(); i++) {
                int[] next = graph[idx].get(i);
                int nextIdx = next[0];
                int weight = next[1];

                if (!visited[nextIdx]) {
                    queue.offer(new int[]{nextIdx, cost + weight});
                }
            }
        }
    }
}
