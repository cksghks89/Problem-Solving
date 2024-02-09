import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] segmentTree;

    private static final int SIZE = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        segmentTree = new int[SIZE * 4][2];
        init(0, SIZE - 1, 1);

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());

            if (A == 1) {
                int B = Integer.parseInt(st.nextToken());
                int[] result = find(0, SIZE - 1, 1, B);
                sb.append(result[0]).append('\n');
                update(0, SIZE - 1, 1, result[0], -1);
            } else {
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                update(0, SIZE - 1, 1, B, C);
            }
        }

        System.out.println(sb);
    }

    private static void init(int start, int end, int node) {
        if (start == end) {
            segmentTree[node] = new int[]{start, 0};
            return;
        }
        int mid = (start + end) / 2;
        init(start, mid, node * 2);
        init(mid + 1, end, node * 2 + 1);
    }

    private static int[] find(int start, int end, int node, int rank) {
        if (start == end) return segmentTree[node];

        int[] l = segmentTree[node * 2];

        int mid = (start + end) / 2;
        if (l[1] >= rank) {
            return find(start, mid, node * 2, rank);
        } else {
            return find(mid + 1, end, node * 2 + 1, rank - l[1]);
        }
    }

    private static void update(int start, int end, int node, int index, int dif) {
        if (index < start || end < index) return;

        segmentTree[node][1] += dif;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}
