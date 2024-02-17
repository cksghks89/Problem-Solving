import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] A;

    private static int M;
    private static long[] segmentTree;
    private static long[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());

        segmentTree = new long[N * 4];
        lazy = new long[N * 4];

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                update(0, N - 1, 1, a - 1, b - 1, c);
            } else {
                int a = Integer.parseInt(st.nextToken());
                long answer = sum(0, N - 1, 1, a - 1, a - 1);
                sb.append(answer).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = A[start];
        int mid = (start + end) / 2;
        return init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
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

        segmentTree[node] += lazy[node] * (end - start + 1);
        if (start != end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static void update(int start, int end, int node, int left, int right, int dif) {
        updateLazy(start, end, node);

        if (left > end || start > right) return;
        if (left <= start && end <= right) {
            segmentTree[node] += (long) dif * (end - start + 1);
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
