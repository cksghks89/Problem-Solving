import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Deque<Integer> deque = new ArrayDeque<>();
    private static int N, K;

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 24; i >= 0; i--) {
            if (Math.pow(2, i) <= N) {
                deque.push((int) Math.pow(2, i));
                N -= (int) Math.pow(2, i);
            }
        }

        if (deque.size() <= K) {
            System.out.println(0);
            return;
        }

        while (deque.size() > K) {
            reduceBottle();
        }

        System.out.println(answer);
    }

    private static void reduceBottle() {
        int bottle = deque.peek();
        answer += bottle;

        while (true) {
            if (deque.isEmpty() || bottle != deque.peek()) {
                deque.push(bottle);
                break;
            }
            bottle += deque.pop();
        }
    }
}
