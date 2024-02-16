import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] segmentTree;
    private static boolean[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        segmentTree = new int[N * 4 + 4];
        lazy = new boolean[N * 4 + 4];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (o == 0) {
                update(0, N - 1, 1, s - 1, t - 1);
            } else {
                int answer = sum(0, N - 1, 1, s - 1, t - 1);
                sb.append(answer).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static int sum(int start, int end, int node, int left, int right) {
        updateLazy(start, end, node);

        if (left > end || start > right) return 0;
        if (left <= start && end <= right) return segmentTree[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void updateLazy(int start, int end, int node) {
        if (!lazy[node]) return;

        segmentTree[node] = end - start + 1 - segmentTree[node];
        if (start != end) {
            lazy[node * 2] = !lazy[node * 2];
            lazy[node * 2 + 1] = !lazy[node * 2 + 1];
        }
        lazy[node] = false;
    }

    private static void update(int start, int end, int node, int left, int right) {
        updateLazy(start, end, node);

        if (left > end || start > right) return;
        if (left <= start && end <= right) {
            segmentTree[node] = end - start + 1 - segmentTree[node];
            if (start != end) {
                lazy[node * 2] = !lazy[node * 2];
                lazy[node * 2 + 1] = !lazy[node * 2 + 1];
            }
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right);
        update(mid + 1, end, node * 2 + 1, left, right);
        segmentTree[node] = segmentTree[node * 2] + segmentTree[node * 2 + 1];
    }
}
