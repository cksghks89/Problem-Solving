import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        int v;
        int idx;
        int removeCnt;

        public Node(int v, int idx) {
            this.v = v;
            this.idx = idx;
        }
    }

    private static int N;
    private static int[] arr;
    private static Node[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        segmentTree = new Node[N * 4];

        init(0, N - 1, 1);

        long cnt = 0;
        for (int i = 0; i < N; i++) {
            Node cur = segmentTree[1];
            int minCnt = minCnt(0, N - 1, 1, 0, cur.idx - 1);

            cnt += cur.idx - minCnt;

            update(0, N - 1, 1, cur.idx);
        }

        System.out.println(cnt);
    }

    private static Node init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = new Node(arr[start], start);

        int mid = (start + end) / 2;
        Node l = init(start, mid, node * 2);
        Node r = init(mid + 1, end, node * 2 + 1);

        return segmentTree[node] = l.v <= r.v ? l : r;
    }

    private static int minCnt(int start, int end, int node, int left, int right) {
        if (left > right || start > right || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return segmentTree[node].removeCnt;
        }

        int mid = (start + end) / 2;
        return minCnt(start, mid, node * 2, left, right) + minCnt(mid + 1, end, node * 2 + 1, left, right);
    }

    private static Node update(int start, int end, int node, int index) {
        if (index < start || index > end) {
            return segmentTree[node];
        }

        if (start == end) {
            segmentTree[node].v = Integer.MAX_VALUE;
            segmentTree[node].removeCnt += 1;
            return segmentTree[node];
        }

        int mid = (start + end) / 2;
        Node l = update(start, mid, node * 2, index);
        Node r = update(mid + 1, end, node * 2 + 1, index);

        segmentTree[node] = l.v <= r.v ? new Node(l.v, l.idx) : new Node(r.v, r.idx);
        segmentTree[node].removeCnt = l.removeCnt + r.removeCnt;

        return segmentTree[node];
    }
}
