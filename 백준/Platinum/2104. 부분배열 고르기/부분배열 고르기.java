import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[] arr;
    private static long[][] tree;

    private static long answer;

    private static final int MAX_NUM = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 0 : 구간합, 1 : 최소값, 2 : 최소값 인덱스
        tree = new long[4 * N][3];

        init(0, N - 1, 1);

        recur(0, N - 1);

        System.out.println(answer);
    }

    private static void recur(int p1, int p2) {
        if (p1 > p2) return;

        long[] segment = getSegment(0, N - 1, 1, p1, p2);
        answer = Math.max(answer, segment[0] * segment[1]);

        recur(p1, (int) segment[2] - 1);
        recur((int) segment[2] + 1, p2);
    }

    private static long[] init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = new long[]{arr[start], arr[start], start};
        }

        int mid = (start + end) / 2;
        long[] l = init(start, mid, node * 2);
        long[] r = init(mid + 1, end, node * 2 + 1);

        if (l[1] <= r[1]) {
            return tree[node] = new long[]{l[0] + r[0], l[1], l[2]};
        } else {
            return tree[node] = new long[]{l[0] + r[0], r[1], r[2]};
        }
    }

    private static long[] getSegment(int start, int end, int node, int left, int right) {
        if (left > end || start > right) return new long[]{0, MAX_NUM, MAX_NUM};
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        long[] l = getSegment(start, mid, node * 2, left, right);
        long[] r = getSegment(mid + 1, end, node * 2 + 1, left, right);

        return new long[]{l[0] + r[0], Math.min(l[1], r[1]), l[1] <= r[1] ? l[2] : r[2]};
    }
}
