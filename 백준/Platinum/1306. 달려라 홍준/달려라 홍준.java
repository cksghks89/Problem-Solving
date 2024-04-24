import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] advertisements;
    private static int[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        advertisements = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        segmentTree = new int[4 * N + 1];

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = M - 1; i < N - M + 1; i++) {
            int maxLight = getLight(0, N - 1, 1, i - M + 1, i + M - 1);
            sb.append(maxLight).append(' ');
        }

        System.out.println(sb);
    }

    private static int init(int start, int end, int node) {
        if (start == end) {
            return segmentTree[node] = advertisements[start];
        }

        int mid = (start + end) / 2;
        return segmentTree[node] = Math.max(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
    }

    private static int getLight(int start, int end, int node, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) {
            return segmentTree[node];
        }

        int mid = (start + end) / 2;
        return Math.max(getLight(start, mid, node * 2, left, right), getLight(mid + 1, end, node * 2 + 1, left, right));
    }
}
