import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr[N] = 1_000_000_001;

        Deque<int[]> stack = new ArrayDeque<>();
        long answer = 0;

        for (int i = 0; i <= N; i++) {
            if (stack.isEmpty()) {
                stack.push(new int[]{i, arr[i]});
                continue;
            }

            while (!stack.isEmpty() && arr[i] >= stack.peek()[1]) {
                answer += i - stack.pop()[0] - 1;
            }

            stack.push(new int[]{i, arr[i]});
        }

        System.out.println(answer);
    }
}
