/*
    Boj 8012. 한동이는 영업사원!
    level. platinum 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_8012_한동이는영업사원 {
    static int n;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] depth;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }
        parent = new int[n + 1][20];
        depth = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0);
        setParent();

        int m = Integer.parseInt(br.readLine());
        int curPos = 1;
        int time = 0;
        for (int i = 0; i < m; i++) {
            int next = Integer.parseInt(br.readLine());

            int qlca = lca(curPos, next);
            time += depth[curPos] + depth[next] - 2 * depth[qlca];
            curPos = next;
        }

        System.out.print(time);
    }

    static void dfs(int cur, int h) {
        depth[cur] = h;
        visited[cur] = true;
        for (int child : graph[cur]) {
            if (!visited[child]) {
                depth[child] = depth[cur] + 1;
                tree[cur].add(child);
                parent[child][0] = cur;
                dfs(child, h + 1);
            }
        }
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

        if (a == b) return b;

        for (int i = 19; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }

    private static void setParent() {
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j <= n; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }
}
