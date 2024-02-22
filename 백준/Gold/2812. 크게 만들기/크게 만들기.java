import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static String num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        num = br.readLine();

        Stack<Character> stack = new Stack<>();

        int cnt = K;
        for (int i = 0; i < N; i++) {
            char cur = num.charAt(i);

            while (!stack.isEmpty() && stack.peek() < cur && cnt >= 1) {
                stack.pop();
                cnt--;
            }
            stack.push(cur);
        }

        // 맨 앞에 0이 나올 경우? -> 존재하지않음
        // K개를 제거후 남은 것은? -> 각 자리의 수가 내림차순이 보장되므로 마지막 자리부터 K개 제거

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        String str = sb.toString();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N - K; i++) {
            answer.append(str.charAt(i));
        }

        System.out.println(answer);
    }
}
