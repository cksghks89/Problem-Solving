import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static int[][] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        segmentTree = new int[4 * N][2];
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
                int[] answer = find(0, N - 1, 1, b - 1, c - 1);
                sb.append(answer[0] + 1).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static int[] init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = new int[]{start, arr[start]};

        int mid = (start + end) / 2;
        int[] l = init(start, mid, node * 2);
        int[] r = init(mid + 1, end, node * 2 + 1);

        if (l[1] == r[1]) {
            segmentTree[node] = l[0] < r[0] ? l : r;
        } else {
            segmentTree[node] = l[1] < r[1] ? l : r;
        }

        return segmentTree[node];
    }

    private static int[] find(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return new int[]{-1, Integer.MAX_VALUE};
        if (left <= start && end <= right) return segmentTree[node];

        int mid = (start + end) / 2;
        int[] l = find(start, mid, node * 2, left, right);
        int[] r = find(mid + 1, end, node * 2 + 1, left, right);

        if (l[1] == r[1]) {
            return l[0] < r[0] ? l : r;
        } else {
            return l[1] < r[1] ? l : r;
        }
    }

    private static int[] update(int start, int end, int node, int index, int val) {
        if (index < start || end < index) return segmentTree[node];
        if (start == end) return segmentTree[node] = new int[]{index, val};

        int mid = (start + end) / 2;
        int[] l = update(start, mid, node * 2, index, val);
        int[] r = update(mid + 1, end, node * 2 + 1, index, val);

        if (l[1] == r[1]) {
            segmentTree[node] = l[0] < r[0] ? l : r;
        } else {
            segmentTree[node] = l[1] < r[1] ? l : r;
        }
        return segmentTree[node];
    }
}
