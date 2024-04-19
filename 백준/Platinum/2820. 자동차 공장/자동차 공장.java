import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static long[] salary;
    private static List<Integer>[] initTree;

    private static int[] l, r, order;
    private static long[] segmentTree;
    private static long[] lazy;
    private static int index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        salary = new long[N];
        initTree = new List[N + 1];
        for (int i = 1; i <= N; i++) initTree[i] = new ArrayList<>();

        // 사장 월급
        salary[0] = Long.parseLong(br.readLine());

        // 사장 하위 직원들의 월급
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            salary[i - 1] = a;
            initTree[p].add(i);
        }

        l = new int[N + 1];
        r = new int[N + 1];
        order = new int[N + 1];

        dfs(1);
        for (int i = 1; i <= N; i++) order[l[i]] = i;

        segmentTree = new long[4 * N + 5];
        lazy = new long[4 * N + 5];

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            char op = st.nextToken().charAt(0);

            if (op == 'p') {
                int a = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                update(0, N - 1, 1, l[a], r[a] - 1, x);
            } else if (op == 'u') {
                int a = Integer.parseInt(st.nextToken());
                long result = query(0, N - 1, 1, l[a] - 1);
                sb.append(result).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static void dfs(int id) {
        l[id] = ++index;
        for (int child : initTree[id]) dfs(child);
        r[id] = index;
    }

    private static void init(int start, int end, int node) {
        if (start == end) {
            segmentTree[node] = salary[order[start + 1] - 1];
            return;
        }

        int mid = (start + end) / 2;
        init(start, mid, node * 2);
        init(mid + 1, end, node * 2 + 1);
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
            if (start == end) segmentTree[node] += value;
            else lazy[node] += value;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right, value);
        update(mid + 1, end, node * 2 + 1, left, right, value);
    }

    private static long query(int start, int end, int node, int idx) {
        updateLazy(start, end, node);
        if (idx < start || end < idx) return 0;
        if (start == end) return segmentTree[node];

        int mid = (start + end) / 2;
        return Math.max(query(start, mid, node * 2, idx), query(mid + 1, end, node * 2 + 1, idx));
    }
}
