import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] arr;
    private static long answer;

    private static class Node {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    private static Node[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            answer = 0;

            segmentTree = new Node[n * 4];
            init(0, n - 1, 1);

            recur(0, n - 1);

            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }

    private static void recur(int left, int right) {
        if (left > right) return;
        if (left == right) {
            answer = Math.max(answer, arr[left]);
            return;
        }

        Node cur = find(0, n - 1, 1, left, right);
        answer = Math.max(answer, (long) (right - left + 1) * cur.value);

        recur(left, cur.index - 1);
        recur(cur.index + 1, right);
    }

    private static Node init(int start, int end, int node) {
        if (start == end) {
            return segmentTree[node] = new Node(arr[start], start);
        }

        int mid = (start + end) / 2;
        Node l = init(start, mid, node * 2);
        Node r = init(mid + 1, end, node * 2 + 1);

        return segmentTree[node] = l.value <= r.value ? new Node(l.value, l.index) : new Node(r.value, r.index);
    }

    private static Node find(int start, int end, int node, int left, int right) {
        if (left > end || start > right) return new Node(Integer.MAX_VALUE, -1);

        if (left <= start && end <= right) {
            return segmentTree[node];
        }

        int mid = (start + end) / 2;
        Node l = find(start, mid, node * 2, left, right);
        Node r = find(mid + 1, end, node * 2 + 1, left, right);

        return l.value <= r.value ? l : r;
    }
}
