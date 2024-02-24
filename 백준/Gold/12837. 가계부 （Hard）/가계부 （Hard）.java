import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, Q;
    private static long[] tree;
    private static int SIZE = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new long[SIZE * 4];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int p = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken());
                update(0, N - 1, 1, p, x);
            } else {
                int p = Integer.parseInt(st.nextToken()) - 1;
                int q = Integer.parseInt(st.nextToken()) - 1;
                long answer = sum(0, N - 1, 1, p, q);
                sb.append(answer).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        if (left > end || start > right) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int index, int dif) {
        if (index < start || end < index) return;
        tree[node] += dif;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}
