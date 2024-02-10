import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static int[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        segmentTree = new int[4 * N];
        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(0, N - 1, 1, b - 1, c);
            } else {
                int answer = find(0, N - 1, 1, b - 1, c - 1);
                sb.append(answer).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static int init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = arr[start];
        int mid = (start + end) / 2;
        return segmentTree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
    }

    private static int find(int start, int end, int node, int left, int right) {
        if (left > end || start > right) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return segmentTree[node];

        int mid = (start + end) / 2;
        return Math.min(find(start, mid, node * 2, left, right), find(mid + 1, end, node * 2 + 1, left, right));
    }

    private static int update(int start, int end, int node, int index, int val) {
        if (index < start || end < index) return segmentTree[node];
        if (start == end) return segmentTree[node] = val;

        int mid = (start + end) / 2;
        return segmentTree[node] = Math.min(update(start, mid, node * 2, index, val), update(mid + 1, end, node * 2 + 1, index, val));
    }
}
