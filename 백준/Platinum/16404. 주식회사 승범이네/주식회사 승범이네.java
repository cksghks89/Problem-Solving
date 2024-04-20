import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static List<Integer>[] graph;

    private static int index = 0;
    private static int[] l, r;
    private static int[] segmentTree;
    private static int[] lazy;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화 -- start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        l = new int[N + 1];
        r = new int[N + 1];
        segmentTree = new int[4 * (N + 1) + 1];
        lazy = new int[4 * (N + 1) + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 2; i <= N; i++) {
            int p = Integer.parseInt(st.nextToken());
            graph[p].add(i);
        }
        // 입력 및 초기화 -- end


        // 쿼리 처리 로직 -- start
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int a = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                update(0, N - 1, 1, l[a] - 1, r[a] - 1, w);
            } else if (op == 2) {
                int a = Integer.parseInt(st.nextToken());
                int balance = getBalance(0, N - 1, 1, l[a] - 1);
                sb.append(balance).append('\n');
            }
        }
        // 쿼리 처리 로직 -- end


        // 출력부
        System.out.println(sb);
    }

    private static void dfs(int id) {
        l[id] = ++index;
        for (int next : graph[id]) dfs(next);
        r[id] = index;
    }

    private static void updateLazy(int start, int end, int node) {
        if (lazy[node] == 0) return;
        segmentTree[node] += lazy[node];
        if (start != end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static void update(int start, int end, int node, int left, int right, int value) {
        if (left > right) return;
        updateLazy(start, end, node);

        if (left > end || start > right) return;
        if (left <= start && end <= right) {
            segmentTree[node] += value;
            if (start != end) lazy[node] += value;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right, value);
        update(mid + 1, end, node * 2 + 1, left, right, value);
    }

    private static int getBalance(int start, int end, int node, int id) {
        updateLazy(start, end, node);

        if (id < start || end < id) return Integer.MIN_VALUE;
        if (start == end) return segmentTree[node];

        int mid = (start + end) / 2;
        return Math.max(getBalance(start, mid, node * 2, id), getBalance(mid + 1, end, node * 2 + 1, id));
    }

}
