import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Main {

    private static int N;
    private static StringBuffer sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        sb = new StringBuffer();
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            subSet(0, new int[N - 1]);
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void subSet(int cnt, int[] seq) {
        if (cnt == N - 1) {
            Queue<Integer> op = new ArrayDeque<>();
            Deque<Integer> num = new ArrayDeque<>();

            num.offer(1);
            for (int i = 2; i <= N; i++) {
                num.offer(i);
                if (seq[i - 2] != 0) {
                    op.offer(seq[i - 2]);
                } else {
                    int cur = num.pollLast();
                    int last = num.pollLast();
                    num.offer(last * 10 + cur);
                }
            }

            while (!op.isEmpty()) {
                int cur = op.poll();
                int left = num.poll();
                int right = num.poll();

                if (cur == 1) {
                    num.offerFirst(left + right);
                } else if (cur == 2) {
                    num.offerFirst(left - right);
                }
            }

            if (num.poll() == 0) {
                for (int i = 1; i < N; i++) {
                    sb.append(i).append(seq[i - 1] == 0 ? ' ' : seq[i - 1] == 1 ? '+' : '-');
                }
                sb.append(N).append('\n');
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            seq[cnt] = i;
            subSet(cnt + 1, seq);
        }
    }
}
