import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static int N;
    private static int[] arr;
    private static int[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            pq.offer(new int[]{i, arr[i]});
        }

        segmentTree = new int[4 * N];
        init(0, N - 1, 1);

        int[] result = new int[N];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            update(0, N - 1, 1, cur[0]);
            int cnt = sum(0, N - 1, 1, 0, cur[0]);
            result[cur[0]] = cur[0] - cnt + 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(result[i]).append('\n');
        System.out.println(sb);
    }

    private static int init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = 1;

        int mid = (start + end) / 2;
        return segmentTree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static int sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return segmentTree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right)
                + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int index) {
        if (index < start || end < index) return;

        segmentTree[node] -= 1;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index);
        update(mid + 1, end, node * 2 + 1, index);
    }
}
