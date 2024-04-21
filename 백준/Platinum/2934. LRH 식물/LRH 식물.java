import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] segmentTree;
    private static int[] lazy;

    private static int SIZE = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        segmentTree = new int[4 * SIZE + 1];
        lazy = new int[4 * SIZE + 1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int leftCount = getFlowerCount(0, SIZE - 1, 1, l);
            int rightCount = getFlowerCount(0, SIZE - 1, 1, r);
            update(0, SIZE - 1, 1, l + 1, r - 1, 1);
            if (leftCount > 0) {
                update(0, SIZE - 1, 1, l, l, -leftCount);
            }
            if (rightCount > 0) {
                update(0, SIZE - 1, 1, r, r, -rightCount);
            }

            sb.append(leftCount + rightCount).append('\n');
        }

        System.out.println(sb);
    }

    private static void updateLazy(int start, int end, int node) {
        if (lazy[node] == 0) return;
        segmentTree[node] = Math.max(0, segmentTree[node] + lazy[node]);
        if (start != end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static void update(int start, int end, int node, int left, int right, int value) {
        updateLazy(start, end, node);
        if (left > end || start > right) return;
        if (left <= start && end <= right) {
            segmentTree[node] = Math.max(0, segmentTree[node] + value);
            if (start != end) lazy[node] += value;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right, value);
        update(mid + 1, end, node * 2 + 1, left, right, value);
    }

    private static int getFlowerCount(int start, int end, int node, int id) {
        updateLazy(start, end, node);
        if (id < start || end < id) return 0;
        if (start == end) return segmentTree[node];

        int mid = (start + end) / 2;
        return Math.max(getFlowerCount(start, mid, node * 2, id), getFlowerCount(mid + 1, end, node * 2 + 1, id));
    }
}
