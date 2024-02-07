import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static int[] nums;
    private static long[] segment;

    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        segment = new long[N * 4];

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        init(0, nums.length - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(0, nums.length - 1, 1, b - 1, c);
            } else {
                long answer = mul(0, nums.length - 1, 1, b - 1, c - 1);
                sb.append(answer).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static long init(int start, int end, int node) {
        if (start == end) return segment[node] = nums[start];

        int mid = (start + end) / 2;
        return segment[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
    }

    private static long mul(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 1;
        if (!isInRange(left) || !isInRange(right)) return 1;

        if (left <= start && end <= right) return segment[node];

        int mid = (start + end) / 2;
        return (mul(start, mid, node * 2, left, right) * mul(mid + 1, end, node * 2 + 1, left, right)) % MOD;
    }

    private static void update(int start, int end, int node, int index, int value) {
        if (index < start || index > end) return;

        if (start == end) {
            segment[node] = value;
            return;
        }

        segment[node] = (mul(0, nums.length - 1, 1, start, index - 1) * mul(0, nums.length - 1, 1, index + 1, end)) % MOD;
        segment[node] *= value;
        segment[node] %= MOD;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, value);
        update(mid + 1, end, node * 2 + 1, index, value);
    }

    private static boolean isInRange(int x) {
        return 0 <= x && x < nums.length;
    }

}
