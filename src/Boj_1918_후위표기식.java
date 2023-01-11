/*
    Boj 1918. 후위 표기식
    level. gold 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj_1918_후위표기식 {
    static Stack<String> operand = new Stack<>();
    static Stack<String> operator = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] expression = br.readLine().split("");

        opControl(expression);

        while (!operator.isEmpty()) {
            calculate();
        }

        System.out.println(operand.pop());
    }

    static void opControl(String[] expression) {
        for (int i = 0; i < expression.length; i++) {
            switch (expression[i]) {
                case "+":
                case "-":
                    while (!operator.isEmpty() && !operator.peek().equals("(")) {
                        calculate();
                    }
                    operator.push(expression[i]);
                    break;
                case "*":
                case "/":
                    if (!operator.isEmpty()) {
                        String peek = operator.peek();
                        if(peek.equals("*") || peek.equals("/")){
                            calculate();
                        }
                    }
                    operator.push(expression[i]);
                    break;
                case "(":
                    operator.push(expression[i]);
                    break;
                case ")":
                    while (!operator.isEmpty() && !operator.peek().equals("(")) {
                        calculate();
                    }
                    operator.pop();
                    break;
                default:
                    operand.push(expression[i]);
            }
        }
    }

    static void calculate() {
        String right = operand.pop();
        String left = operand.pop();
        String op = operator.pop();

        operand.push(left + right + op);
    }
}