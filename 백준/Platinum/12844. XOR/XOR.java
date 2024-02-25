import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] arr;

    private static int[] tree;
    private static int[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[500_001];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        tree = new int[500_000 * 4 + 1];
        lazy = new int[500_000 * 4 + 1];

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int k = Integer.parseInt(st.nextToken());
                update(0, N - 1, 1, Math.min(a, b), Math.max(a, b), k);
            } else {
                int answer = getXor(0, N - 1, 1, Math.min(a, b), Math.max(a, b));
                sb.append(answer).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = (init(start, mid, node * 2) ^ init(mid + 1, end, node * 2 + 1));
    }

    private static int getXor(int start, int end, int node, int left, int right) {
        updateLazy(start, end, node);
        if (left > end || start > right) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return getXor(start, mid, node * 2, left, right) ^ getXor(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void updateLazy(int start, int end, int node) {
        if (lazy[node] == 0) return;

        if ((end - start + 1) % 2 == 1) {
            tree[node] ^= lazy[node];
        }
        if (start != end) {
            lazy[node * 2] ^= lazy[node];
            lazy[node * 2 + 1] ^= lazy[node];
        }
        lazy[node] = 0;
    }

    private static void update(int start, int end, int node, int left, int right, int k) {
        updateLazy(start, end, node);
        if (left > end || start > right) return;

        if (left <= start && end <= right) {
            if ((end - start + 1) % 2 == 1) {
                tree[node] ^= k;
            }
            if (start != end) {
                lazy[node * 2] ^= k;
                lazy[node * 2 + 1] ^= k;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right, k);
        update(mid + 1, end, node * 2 + 1, left, right, k);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }
}
