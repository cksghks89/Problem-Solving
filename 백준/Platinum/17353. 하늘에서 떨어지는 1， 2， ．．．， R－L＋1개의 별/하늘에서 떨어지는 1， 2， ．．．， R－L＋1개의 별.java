import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static long[] tree;
    private static long[][] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        tree = new long[4 * N];
        lazy = new long[4 * N][3];  // l - 왼쪽 시작 값, r - 마지막 값, cnt - 노드 방문 카운트

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int L = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());
                update(0, N - 1, 1, L - 1, R - 1, 1, R - L + 1);
            } else {
                int x = Integer.parseInt(st.nextToken());
                long ans = sum(0, N - 1, 1, x - 1);
                sb.append(ans).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static void init(int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        init(start, mid, node * 2);
        init(mid + 1, end, node * 2 + 1);
    }

    private static long sum(int start, int end, int node, int index) {
        updateLazy(start, end, node);

        if (index < start || end < index) return 0;
        if (start == end) return tree[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, index) + sum(mid + 1, end, node * 2 + 1, index);
    }

    private static void updateLazy(int start, int end, int node) {
        if (lazy[node][2] == 0) return;

        int mid = (start + end) / 2;

        if (start == end) {
            tree[node] += lazy[node][0];
        } else {
            lazy[node * 2][0] += lazy[node][0];
            lazy[node * 2][1] += lazy[node][0] + (mid - start) * lazy[node][2];
            lazy[node * 2][2] += lazy[node][2];

            lazy[node * 2 + 1][0] += lazy[node][0] + (mid - start + 1) * lazy[node][2];
            lazy[node * 2 + 1][1] += lazy[node][1];
            lazy[node * 2 + 1][2] += lazy[node][2];
        }

        lazy[node][0] = 0;
        lazy[node][1] = 0;
        lazy[node][2] = 0;
    }

    private static void update(int start, int end, int node, int left, int right, int l, int r) {
        updateLazy(start, end, node);
        if (left > end || start > right) return;

        int mid = (start + end) / 2;
        int leftCnt = getLeftCnt(start, mid, left, right);

        if (left <= start && end <= right) {
            if (start == end) {
                tree[node] += l;
            } else {
                lazy[node * 2][0] += l;
                lazy[node * 2][1] += l + leftCnt - 1;
                lazy[node * 2][2] += 1;

                lazy[node * 2 + 1][0] += l + leftCnt;
                lazy[node * 2 + 1][1] += r;
                lazy[node * 2 + 1][2] += 1;
            }
            return;
        }

        update(start, mid, node * 2, left, right, l, l + leftCnt - 1);
        update(mid + 1, end, node * 2 + 1, left, right, l + leftCnt, r);
    }

    private static int getLeftCnt(int start, int mid, int left, int right) {
        return Math.max(Math.min(right, mid) - Math.max(start, left) + 1, 0);
    }
}