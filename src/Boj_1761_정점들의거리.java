/*
    Boj 1761. 정점들의 거리
    level. platinum 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1761_정점들의거리 {
    static int N;
    static ArrayList<int[]>[] graph;
    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        tree = new ArrayList[N + 1];
        parent = new int[N + 1][2];
        depth = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        setTree();

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(lca(u, v)).append('\n');
        }
        System.out.print(sb.toString());
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        int dist = 0;

        while (depth[a] != depth[b]) {
            dist += parent[a][1];
            a = parent[a][0];
        }

        if (a == b) return dist;

        while (a != b) {
            dist += parent[a][1];
            dist += parent[b][1];
            a = parent[a][0];
            b = parent[b][0];
        }

        return dist;
    }

    static void setTree() {
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
                parent[child[0]][1] = child[1];
                depth[child[0]] = depth[cur] + 1;
                queue.offer(child[0]);
            }
        }
    }
}
