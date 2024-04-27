import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, Q1, Q2;
    private static long[] arr;
    private static long[] segmentTree;
    private static long[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q1 = Integer.parseInt(st.nextToken());
        Q2 = Integer.parseInt(st.nextToken());

        arr = new long[N];
        segmentTree = new long[4 * N + 1];
        lazy = new long[4 * N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(st.nextToken());

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q1 + Q2; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                long answer = query(0, N - 1, 1, n - 1, m - 1);
                sb.append(answer).append('\n');
            } else if (op == 2) {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                update(0, N - 1, 1, s - 1, e - 1, l);
            }
        }

        System.out.println(sb);
    }

    private static long init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = arr[start];
        int mid = (start + end) / 2;
        return segmentTree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static void updateLazy(int start, int end, int node) {
        if (lazy[node] == 0) return;
        segmentTree[node] += lazy[node] * (end - start + 1);
        if (start != end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static void update(int start, int end, int node, int left, int right, long value) {
        updateLazy(start, end, node);
        if (left > end || start > right) return;
        if (left <= start && end <= right) {
            segmentTree[node] += value * (end - start + 1);
            if (start != end) {
                lazy[node * 2] += value;
                lazy[node * 2 + 1] += value;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right, value);
        update(mid + 1, end, node * 2 + 1, left, right, value);
        segmentTree[node] = segmentTree[node * 2] + segmentTree[node * 2 + 1];
    }

    private static long query(int start, int end, int node, int left, int right) {
        updateLazy(start, end, node);
        if (left > end || start > right) return 0;
        if (left <= start && end <= right) {
            return segmentTree[node];
        }

        int mid = (start + end) / 2;
        return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
    }

}
