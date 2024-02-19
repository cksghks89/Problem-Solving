import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[] arr;

    private static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        StringBuilder sb = new StringBuilder();
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            tree = new int[N * 4][2];

            init(0, N - 1, 1);

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char op = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (op == 'C') {
                    update(0, N - 1, 1, a - 1, b);
                } else {
                    int[] answer = mul(0, N - 1, 1, a - 1, b - 1);
                    if (answer[0] > 0) sb.append('0');
                    else if (answer[1] % 2 == 1) sb.append('-');
                    else sb.append('+');
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static int[] init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = new int[]{arr[start] == 0 ? 1 : 0, arr[start] < 0 ? 1 : 0};
        }
        int mid = (start + end) / 2;
        int[] left = init(start, mid, node * 2);
        int[] right = init(mid + 1, end, node * 2 + 1);
        return tree[node] = new int[]{left[0] + right[0], left[1] + right[1]};
    }

    private static int[] mul(int start, int end, int node, int left, int right) {
        if (left > end || start > right || left > right) return new int[]{0, 0};
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        int[] l = mul(start, mid, node * 2, left, right);
        int[] r = mul(mid + 1, end, node * 2 + 1, left, right);
        return new int[]{l[0] + r[0], l[1] + r[1]};
    }

    private static void update(int start, int end, int node, int index, int dif) {
        if (index < start || index > end) return;

        int[] left = mul(0, N - 1, 1, start, index - 1);
        int[] right = mul(0, N - 1, 1, index + 1, end);
        tree[node] = new int[]{dif == 0 ? 1 : 0, dif < 0 ? 1 : 0};
        tree[node][0] += left[0] + right[0];
        tree[node][1] += left[1] + right[1];

        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}
