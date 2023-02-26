/*
    Boj 3176. 도로 네트워크
    level. platinum 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_3176_도로네트워크 {
    static int N;
    static ArrayList<int[]>[] graph;
    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] depth;
    static int[][][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        tree = new ArrayList[N + 1];
        parent = new int[N + 1][20];
        depth = new int[N + 1];
        cost = new int[N + 1][20][2];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        setTree();

        for (int i = 1; i < 20; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
                List<Integer> temp = new ArrayList<>();
                int[] ints = cost[parent[j][i - 1]][i - 1];
                temp.add(ints[0]);
                temp.add(ints[1]);
                temp.add(cost[j][i - 1][0]);
                temp.add(cost[j][i - 1][1]);
                Collections.sort(temp);
                cost[j][i][0] = temp.get(0);
                cost[j][i][1] = temp.get(3);
            }
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[] result = lca(D, E);
            sb.append(result[0]).append(' ').append(result[1]).append('\n');
        }

        System.out.print(sb.toString());
    }

    static int[] lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 19; i >= 0; i--) {
            if (depth[a] - depth[b] >= (1 << i)) {
                min = Math.min(min, cost[a][i][0]);
                max = Math.max(max, cost[a][i][1]);
                a = parent[a][i];
            }
        }

        if (a == b) return new int[]{min, max};

        for (int i = 19; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                min = Math.min(min, cost[a][i][0]);
                min = Math.min(min, cost[b][i][0]);
                max = Math.max(max, cost[a][i][1]);
                max = Math.max(max, cost[b][i][1]);
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        min = Math.min(min, cost[a][0][0]);
        min = Math.min(min, cost[b][0][0]);
        max = Math.max(max, cost[a][0][1]);
        max = Math.max(max, cost[b][0][1]);

        return new int[]{min, max};
    }

    private static void setTree() {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (visited[cur]) continue;
            visited[cur] = true;

            for (int[] child : graph[cur]) {
                if (visited[child[0]]) continue;
                tree[cur].add(child[0]);
                parent[child[0]][0] = cur;
                depth[child[0]] = depth[cur] + 1;
                cost[child[0]][0][0] = child[1];
                cost[child[0]][0][1] = child[1];
                queue.offer(child[0]);
            }
        }
    }
}
