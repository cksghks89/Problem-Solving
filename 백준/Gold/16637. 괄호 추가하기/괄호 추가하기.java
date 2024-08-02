import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static int N;
    private static long[] number;
    private static char[] operation;
    private static boolean[] priority;
    private static long answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        number = new long[N / 2 + 1];
        operation = new char[N / 2];
        priority = new boolean[N / 2];

        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                number[i / 2] = input.charAt(i) - '0';
            } else {
                operation[i / 2] = input.charAt(i);
            }
        }

        subset(0);

        System.out.println(answer);
    }

    private static void subset(int cnt) {
        if (cnt >= N / 2) {
            answer = Math.max(answer, getCalculate());
            return;
        }

        subset(cnt + 1);
        priority[cnt] = true;
        subset(cnt + 2);
        priority[cnt] = false;
    }

    private static long getCalculate() {
        if (N == 1) return number[0];

        Deque<Long> numDeq = new ArrayDeque<>();
        Deque<Character> opDeq = new ArrayDeque<>();
        for (int i = 0; i < N / 2; i++) {
            if (!priority[i]) {
                numDeq.addLast(number[i]);
                opDeq.addLast(operation[i]);
                if (i == N / 2 - 1) {
                    numDeq.addLast(number[i + 1]);
                }
            } else {
                numDeq.addLast(calc(number[i], number[i + 1], operation[i]));
                if (i != N / 2 - 1) {
                    opDeq.addLast(operation[i + 1]);
                }
                i++;

                if (i + 1 == N / 2) {
                    numDeq.addLast(number[i + 1]);
                }
            }
        }

        while (!opDeq.isEmpty()) {
            char cur = opDeq.removeFirst();
            long a = numDeq.removeFirst();
            long b = numDeq.removeFirst();
            numDeq.addFirst(calc(a, b, cur));
        }

        return numDeq.removeFirst();
    }

    private static long calc(long a, long b, char op) {
        if (op == '+') return a + b;
        else if (op == '-') return a - b;
        else if (op == '*') return a * b;
        else return -1;
    }
}
