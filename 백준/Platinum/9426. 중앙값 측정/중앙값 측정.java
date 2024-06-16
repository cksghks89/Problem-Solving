import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[] segmentTree;

    private static final int MAX_VAL = 65535;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        segmentTree = new int[4 * (MAX_VAL + 1)];

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


        for (int i = 0; i < K - 1; i++) {
            update(0, MAX_VAL, 1, arr[i], 1);
        }

        long sum = 0;
        for (int i = K - 1; i < N; i++) {
            update(0, MAX_VAL, 1, arr[i], 1);
            sum += getMedium(0, MAX_VAL, 1, (K + 1) / 2);
            update(0, MAX_VAL, 1, arr[i - K + 1], -1);
        }

        System.out.println(sum);
    }

    private static int getMedium(int start, int end, int node, int k) {
        if (start == end) return start;

        int left = segmentTree[node * 2];

        int mid = (start + end) / 2;

        if (k <= left) {
            return getMedium(start, mid, node * 2, k);
        } else {
            return getMedium(mid + 1, end, node * 2 + 1, k - left);
        }
    }

    private static void update(int start, int end, int node, int n, int value) {
        if (n < start || end < n) return;
        segmentTree[node] += value;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, n, value);
        update(mid + 1, end, node * 2 + 1, n, value);
    }
}
