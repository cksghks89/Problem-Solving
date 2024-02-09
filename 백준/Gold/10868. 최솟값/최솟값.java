import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] arr;
    private static int[][] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        segmentTree = new int[N * 4][2];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] result = find(0, N - 1, 1, a - 1, b - 1);
            sb.append(result[0]).append('\n');
        }

        System.out.println(sb);
    }

    private static int[] init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = new int[]{arr[start], arr[start]};

        int mid = (start + end) / 2;
        int[] l = init(start, mid, node * 2);
        int[] r = init(mid + 1, end, node * 2 + 1);

        return segmentTree[node] = new int[]{Math.min(l[0], r[0]), Math.max(l[1], r[1])};
    }

    private static int[] find(int start, int end, int node, int left, int right) {
        if (left > end || start > right) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        if (left <= start && end <= right) return segmentTree[node];

        int mid = (start + end) / 2;
        int[] l = find(start, mid, node * 2, left, right);
        int[] r = find(mid + 1, end, node * 2 + 1, left, right);

        return new int[]{Math.min(l[0], r[0]), Math.max(l[1], r[1])};
    }
}
