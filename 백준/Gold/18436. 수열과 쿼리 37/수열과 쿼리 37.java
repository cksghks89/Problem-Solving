import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int M = Integer.parseInt(br.readLine());

        // 0: 짝수개수, 1: 홀수개수
        tree = new int[4 * N][2];

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 1) {
                update(0, N - 1, 1, a - 1, b);
                arr[a - 1] = b;
            } else {
                int[] sum = sum(0, N - 1, 1, a - 1, b - 1);
                if (op == 2) {
                    sb.append(sum[0]).append('\n');
                } else {
                    sb.append(sum[1]).append('\n');
                }
            }
        }

        System.out.println(sb);
    }

    private static int[] init(int start, int end, int node) {
        if (start == end) {
            if (arr[start] % 2 == 0) tree[node][0] += 1;
            else tree[node][1] += 1;

            return tree[node];
        }

        int mid = (start + end) / 2;
        int[] l = init(start, mid, node * 2);
        int[] r = init(mid + 1, end, node * 2 + 1);

        return tree[node] = new int[]{l[0] + r[0], l[1] + r[1]};
    }

    private static int[] sum(int start, int end, int node, int left, int right) {
        if (left > end || start > right) return new int[]{0, 0};
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int[] l = sum(start, mid, node * 2, left, right);
        int[] r = sum(mid + 1, end, node * 2 + 1, left, right);
        return new int[]{l[0] + r[0], l[1] + r[1]};
    }

    private static void update(int start, int end, int node, int index, int dif) {
        if (index < start || end < index) return;

        if (arr[index] % 2 == 0) tree[node][0] -= 1;
        else tree[node][1] -= 1;

        if (dif % 2 == 0) tree[node][0] += 1;
        else tree[node][1] += 1;

        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}
