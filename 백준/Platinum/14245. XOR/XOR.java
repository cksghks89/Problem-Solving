import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] arr;
    private static int[] tree;
    private static int[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        m = Integer.parseInt(br.readLine());

        tree = new int[(n + 1) * 4];
        lazy = new int[(n + 1) * 4];

        init(0, n - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                update(0, n - 1, 1, a, b, c);
            } else {
                int a = Integer.parseInt(st.nextToken());
                int ans = getNum(0, n - 1, 1, a);
                sb.append(ans).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return init(start, mid, node * 2) ^ init(mid + 1, end, node * 2 + 1);
    }

    private static int getNum(int start, int end, int node, int index) {
        updateLazy(start, end, node);

        if (index < start || end < index) return 0;
        if (start == end) return tree[node];
        
        int mid = (start + end) / 2;
        return getNum(start, mid, node * 2, index) ^ getNum(mid + 1, end, node * 2 + 1, index);
    }

    private static void updateLazy(int start, int end, int node) {
        if (lazy[node] == 0) return;

        if ((end - start + 1) % 2 == 1) tree[node] ^= lazy[node];
        if (start != end) {
            lazy[node * 2] ^= lazy[node];
            lazy[node * 2 + 1] ^= lazy[node];
        }
        lazy[node] = 0;
    }

    private static void update(int start, int end, int node, int left, int right, int c) {
        updateLazy(start, end, node);

        if (left > end || start > right) return;
        if (left <= start && end <= right) {
            if ((end - start + 1) % 2 == 1) tree[node] ^= c;
            if (start == end) return;
            lazy[node * 2] ^= c;
            lazy[node * 2 + 1] ^= c;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right, c);
        update(mid + 1, end, node * 2 + 1, left, right, c);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }
}
