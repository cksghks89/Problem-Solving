/*
    Boj 3584. 가장 가까운 공통조상
    level. gold 4
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_3584_가장가까운공통조상 {
    static int N;
    static ArrayList<Integer>[] tree;
    static ArrayList<Integer>[] graph;
    static int[][] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            tree = new ArrayList[N + 1];
            graph = new ArrayList[N + 1];
            parent = new int[N + 1][20];
            depth = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                tree[i] = new ArrayList<>();
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[p].add(c);
                graph[c].add(p);
                parent[c][0] = p;
            }

            setTree();

            for (int i = 1; i < 20; i++) {
                for (int j = 1; j <= N; j++) {
                    parent[j][i] = parent[parent[j][i - 1]][i - 1];
                }
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append('\n');
        }
        System.out.print(sb.toString());
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = 19; i >= 0; i--) {
            if (depth[a] - depth[b] >= (1 << i)) {
                a = parent[a][i];
            }
        }

        if (a == b) return a;

        for (int i = 19; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }

    static void setTree() {
        int root = 0;
        for (int i = 1; i <= N; i++) {
            if (parent[i][0] == 0) {
                root = i;
                break;
            }
        }
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (visited[cur]) continue;
            visited[cur] = true;

            for (int child : graph[cur]) {
                if (visited[child]) continue;
                depth[child] = depth[cur] + 1;
                tree[cur].add(child);
                queue.offer(child);
            }
        }
    }
}
