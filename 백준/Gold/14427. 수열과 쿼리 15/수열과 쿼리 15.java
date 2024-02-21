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
        tree = new int[N * 4][2];

        init(0, N - 1, 1);

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                update(0, N - 1, 1, a - 1, b);
                arr[a - 1] = b;
            } else {
                int minIdx = tree[1][0] + 1;
                sb.append(minIdx).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static int[] init(int start, int end, int node) {
        if (start == end) return tree[node] = new int[]{start, arr[start]};
        int mid = (start + end) / 2;
        int[] l = init(start, mid, node * 2);
        int[] r = init(mid + 1, end, node * 2 + 1);

        return tree[node] = l[1] <= r[1] ? l : r;
    }

    private static void update(int start, int end, int node, int index, int dif) {
        if (index < start || end < index) return;

        if (start == end) {
            tree[node] = new int[]{index, dif};
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
        tree[node] = tree[node * 2][1] <= tree[node * 2 + 1][1] ? tree[node * 2] : tree[node * 2 + 1];
    }
}
