import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, H;
    private static int[] tree;
    private static int[] lazy;

    public static void main(String[] args) throws IOException {
        // N은 짝수, H는 높이
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tree = new int[4 * H];
        lazy = new int[4 * H];

        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                update(0, H - 1, 1, 0, cur - 1);
            } else {
                update(0, H - 1, 1, H - cur, H - 1);
            }
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            int cur = sum(0, H - 1, 1, i);
            if (cur < min) {
                min = cur;
                cnt = 1;
            } else if (cur == min) {
                cnt += 1;
            }
        }

        System.out.println(min + " " + cnt);
    }

    private static int sum(int start, int end, int node, int index) {
        updateLazy(start, end, node);
        if (index < start || end < index) return 0;
        if (start == end) return tree[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, index) + sum(mid + 1, end, node * 2 + 1, index);
    }

    private static void updateLazy(int start, int end, int node) {
        if (lazy[node] == 0) return;

        tree[node] += (end - start + 1) * lazy[node];
        if (start != end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static void update(int start, int end, int node, int left, int right) {
        updateLazy(start, end, node);

        if (left > end || start > right) return;
        if (left <= start && end <= right) {
            tree[node] += (end - start + 1);
            if (start != end) {
                lazy[node * 2] += 1;
                lazy[node * 2 + 1] += 1;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right);
        update(mid + 1, end, node * 2 + 1, left, right);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}
