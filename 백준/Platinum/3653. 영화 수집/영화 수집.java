import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] arr;
    private static Map<Integer, Integer> map;
    private static int[] segmentTree;

    private static final int SIZE = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            arr = new int[SIZE + n];
            map = new HashMap<>();
            segmentTree = new int[4 * (SIZE + n)];

            int val = 1;
            for (int i = SIZE; i < SIZE + n; i++) {
                arr[i] = 1;
                map.put(val++, i);
            }

            init(0, SIZE + n - 1, 1);

            st = new StringTokenizer(br.readLine());
            int idx = SIZE - 1;
            for (int i = 0; i < m; i++) {
                int index = Integer.parseInt(st.nextToken());
                int cur = map.get(index);
                map.put(index, idx);

                int answer = sum(0, SIZE + n - 1, 1, 0, cur - 1);


                update(0, SIZE + n - 1, 1, cur, -1);
                update(0, SIZE + n - 1, 1, idx--, 1);

                sb.append(answer).append(' ');
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = arr[start] != 0 ? 1 : 0;
        int mid = (start + end) / 2;
        return segmentTree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static int sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return segmentTree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int index, int dif) {
        if (index < start || end < index) return;

        segmentTree[node] += dif;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}
