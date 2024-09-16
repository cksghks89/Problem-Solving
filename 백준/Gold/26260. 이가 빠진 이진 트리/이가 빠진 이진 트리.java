import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;
    private static int[] tree;

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        tree = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int replace = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i + 1] = Integer.parseInt(st.nextToken());
            if (arr[i + 1] == -1) {
                arr[i + 1] = replace;
            }
        }

        Arrays.sort(arr);

        recur(1, N, 1);

        postOrder(1);

        System.out.println(answer);
    }

    private static void postOrder(int idx) {
        if (idx > N) return;

        postOrder(idx * 2);
        postOrder(idx * 2 + 1);
        answer.append(tree[idx]).append(' ');
    }

    private static void recur(int start, int end, int idx) {
        if (idx > N) return;

        int mid = (start + end) / 2;

        tree[idx] = arr[mid];
        recur(start, mid - 1, idx * 2);
        recur(mid + 1, end, idx * 2 + 1);
    }
}
