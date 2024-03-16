import javax.swing.plaf.BorderUIResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static int N;
    private static PriorityQueue<Integer> positive;
    private static PriorityQueue<Integer> negative;
    private static boolean isExistZero;
    private static int oneCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        positive = new PriorityQueue<>((o1, o2) -> o2 - o1);
        negative = new PriorityQueue<>((o1, o2) -> o1 - o2);
        isExistZero = false;
        oneCnt = 0;

        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(br.readLine());

            if (cur == 1) oneCnt += 1;
            else if (cur < 0) negative.add(cur);
            else if (cur > 0) positive.add(cur);
            else isExistZero = true;
        }

        int answer = 0;
        while (!positive.isEmpty()) {
            if (positive.size() >= 2) {
                answer += positive.poll() * positive.poll();
            } else {
                answer += positive.poll();
            }
        }

        while (negative.size() >= 2) {
            answer += negative.poll() * negative.poll();
        }

        if (!negative.isEmpty() && !isExistZero) {
            answer += negative.poll();
        }

        System.out.println(answer + oneCnt);
    }
}
