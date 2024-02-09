import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static class Node {
        int v;
        int idx;

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
        segmentTree = new Node[N * 4];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(0, N - 1, 1);

        System.out.println(recur(0, N - 1));
    }

    private static int recur(int left, int right) {
        if (left > right) return -1;
        if (left == right) return arr[left];

        Node cur = find(0, N - 1, 1, left, right);

        int curArea = cur.v * (right - left + 1);
        int l = recur(left, cur.idx - 1);
        int r = recur(cur.idx + 1, right);

        return Math.max(curArea, Math.max(l, r));
    }

    private static Node init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = new Node(arr[start], start);
        int mid = (start + end) / 2;
        Node l = init(start, mid, node * 2);
        Node r = init(mid + 1, end, node * 2 + 1);
        return segmentTree[node] = l.v <= r.v ? l : r;
    }

    private static Node find(int start, int end, int node, int left, int right) {
        if (left > right || left > end || right < start) return new Node(Integer.MAX_VALUE, -1);
        if (left <= start && end <= right) {
            return segmentTree[node];
        }

        int mid = (start + end) / 2;
        Node l = find(start, mid, node * 2, left, right);
        Node r = find(mid + 1, end, node * 2 + 1, left, right);

        return l.v <= r.v ? l : r;
    }
}
