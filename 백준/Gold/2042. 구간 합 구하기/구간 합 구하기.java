import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        tree = new long[N + 1];
        long[] input = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            input[i] = Long.parseLong(br.readLine());
            plus(i, input[i]);
        }

        List<Long> answer = new ArrayList<>(K);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) {
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                plus(b, c - input[b]);
                input[b] = c;
            } else if (a == 2) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                answer.add(sum(c) - sum(b - 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (long ans : answer) {
            sb.append(ans).append('\n');
        }

        System.out.print(sb);

    }

    static long sum(int index) {
        long result = 0;
        while (index > 0) {
            result += tree[index];
            index -= (index & -index);
        }
        return result;
    }

    static void plus(int index, long value) {
        while (index < tree.length) {
            tree[index] += value;
            index += (index & -index);
        }
    }
}
