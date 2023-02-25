/*
    Boj 11437. LCA
    level. gold 4
    solved by 송찬환
 */
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_11437_LCA {
    static int N;
    static ArrayList<Integer>[] tree;
    static ArrayList<Integer>[] graph;
    static int[][] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        graph = new ArrayList[N + 1];
        parent = new int[N + 1][21];
        depth = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
            graph[i] = new ArrayList<>();
        }

        // 입력값을 그래프로 세팅
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        // 그래프를 트리로 만들기
        setTree();

        // parent dp 세팅
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }

        // 쿼리 입력 및 출력 처리
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append('\n');
        }

        System.out.print(sb.toString());
    }

    static void setTree() {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (visited[cur]) continue;
            visited[cur] = true;

            for (int child : graph[cur]) {
                if (visited[child]) continue;
                tree[cur].add(child);
                parent[child][0] = cur;
                depth[child] = depth[cur] + 1;
                queue.offer(child);
            }
        }
    }

    static int lca(int a, int b) {
        // a 노드의 깊이가 항상 깊게 세팅
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // a 노드와 b 노드의 depth 를 같게 만들기
        for (int i = 19; i >= 0; i--) {
            if (depth[a] - depth[b] >= (1 << i)) {
                a = parent[a][i];
            }
        }

        // depth 를 같게 했더니 a 와 b가 같으면 공통조상이 b 이므로 리턴
        if (a == b) return b;

        // 두 노드가 공통 조상 바로 아래로 오도록 조정
        for (int i = 19; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
}
