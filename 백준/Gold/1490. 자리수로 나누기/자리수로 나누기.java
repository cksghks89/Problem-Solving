import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    private static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1<<16);

        N = Long.parseLong(br.readLine());

        long answer = bfs(N);

        System.out.println(answer);
    }

    private static long bfs(long num) {
        Queue<Long> queue = new ArrayDeque<>();
        queue.offer(num);

        while (!queue.isEmpty()) {
            long cur = queue.poll();

            if (isDivided(cur)) {
                return cur;
            }

            for (int i = 0; i < 10; i++) {
                queue.offer(cur * 10 + i);
            }
        }
        return -1;
    }

    private static boolean isDivided(long num) {
        long d = N;
        while (d != 0) {
            if (d % 10 != 0 && num % (d % 10) != 0) return false;
            d /= 10;
        }
        return true;
    }
}
