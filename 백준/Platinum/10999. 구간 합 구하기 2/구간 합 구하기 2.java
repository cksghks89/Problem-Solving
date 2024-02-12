import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;

    private static long[] arr;
    private static long[] segmentTree;
    private static long[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        segmentTree = new long[4 * N];
        lazy = new long[4 * N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                update(0, N - 1, 1, b - 1, c - 1, d);
            } else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long sum = sum(0, N - 1, 1, b - 1, c - 1);
                sb.append(sum).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = arr[start];
        int mid = (start + end) / 2;
        return segmentTree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        updateLazy(start, end, node);

        if (left > end || start > right) return 0;
        if (left <= start && end <= right) return segmentTree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void updateLazy(int start, int end, int node) {
        if (lazy[node] == 0) return;

        segmentTree[node] += (lazy[node] * (end - start + 1));
        if (start != end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static void update(int start, int end, int node, int left, int right, long dif) {
        updateLazy(start, end, node);

        if (left > end || start > right) return;
        if (left <= start && end <= right) {
            segmentTree[node] += dif * (end - start + 1);

            if (start != end) {
                lazy[node * 2] += dif;
                lazy[node * 2 + 1] += dif;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right, dif);
        update(mid + 1, end, node * 2 + 1, left, right, dif);
        segmentTree[node] = segmentTree[node * 2] + segmentTree[node * 2 + 1];
    }

}
