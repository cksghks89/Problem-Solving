/*
    Boj 9935. 문자열 폭발
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj_9935_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        char[] bombStr = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length; i++) {
            stack.add(str[i]);

            if (stack.size() >= bombStr.length && stack.peek() == bombStr[bombStr.length - 1]) {
                bombCheck(stack, bombStr);
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }
            sb.reverse();
            System.out.println(sb.toString());
        }
    }

    static void bombCheck(Stack<Character> stack, char[] bombStr) {
        Stack<Character> temp = new Stack<>();

        for (int i = bombStr.length - 1; i >= 0; i--) {
            if (bombStr[i] == stack.peek()) {
                temp.add(stack.pop());
            } else {
                while(!temp.isEmpty()){
                    stack.add(temp.pop());
                }
                break;
            }
        }
    }
}
