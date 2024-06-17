import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static int[] segmentTree;
    private static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        segmentTree = new int[4 * N + 1];
        answer = new int[N];

        init(0, N - 1, 1);
        for (int i = 1; i <= N; i++) {
            int cur = Integer.parseInt(br.readLine());
            setIndex(0, N - 1, 1, cur + 1, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(answer[i]).append('\n');

        System.out.println(sb);
    }

    private static int init(int start, int end, int node) {
        if (start == end) return segmentTree[node] = 1;
        int mid = (start + end) / 2;
        return segmentTree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static void setIndex(int start, int end, int node, int seq, int id) {
        if (start == end) {
            answer[start] = id;
            segmentTree[node] = 0;
            return;
        }

        segmentTree[node] -= 1;
        int left = segmentTree[node * 2];
        int mid = (start + end) / 2;

        if (seq <= left) {
            setIndex(start, mid, node * 2, seq, id);
        } else {
            setIndex(mid + 1, end, node * 2 + 1, seq - left, id);
        }
    }
}
