import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        // 스택?
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (stack.size() < 3) {
                stack.push(input.charAt(i));
            } else {
                stack.push(input.charAt(i));
                while (stack.size() >= 4 && isPPAP(stack)) {
                }
            }
        }

        if (stack.size() == 1 && stack.peek() == 'P') {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }

    private static boolean isPPAP(Stack<Character> stack) {
        char s4 = stack.pop();
        char s3 = stack.pop();
        char s2 = stack.pop();
        char s1 = stack.pop();

        if (s1 == 'P' && s2 == 'P' && s3 == 'A' && s4 == 'P') {
            stack.push('P');
            return true;
        }
        stack.push(s1);
        stack.push(s2);
        stack.push(s3);
        stack.push(s4);
        return false;
    }
}
