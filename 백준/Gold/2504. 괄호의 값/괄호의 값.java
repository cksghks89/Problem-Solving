import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        Deque<Integer> deque = new ArrayDeque<>();

        boolean isPossible = true;
        // 불가능한 경우 : 닫는 괄호 앞에 다른 괄호가 나왔을 경우
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == '[') {
                deque.add(-1 * input.charAt(i));
                continue;
            }

            if (deque.isEmpty() || (deque.peekLast() > 0 && deque.size() < 2)) {
                isPossible = false;
                break;
            }

            if (input.charAt(i) == ')') {
                if (deque.peekLast() == -1 * '(') {
                    deque.pollLast();

                    if (!deque.isEmpty() && deque.peekLast() > 0) {
                        deque.add(deque.pollLast() + 2);
                    } else {
                        deque.add(2);
                    }
                } else if (deque.peekLast() > 0) {
                    int num = deque.pollLast();
                    int open = deque.pollLast();

                    if (open != -1 * '(') {
                        isPossible = false;
                        break;
                    }

                    if (!deque.isEmpty() && deque.peekLast() > 0) {
                        deque.add(deque.pollLast() + num * 2);
                    } else {
                        deque.add(num * 2);
                    }
                } else {
                    isPossible = false;
                    break;
                }
            } else if (input.charAt(i) == ']') {
                if (deque.peekLast() == -1 * '[') {
                    deque.pollLast();
                    if (!deque.isEmpty() && deque.peekLast() > 0) {
                        deque.add(deque.pollLast() + 3);
                    } else {
                        deque.add(3);
                    }
                } else if (deque.peekLast() > 0) {
                    int num = deque.pollLast();
                    int open = deque.pollLast();

                    if (open != -1 * '[') {
                        isPossible = false;
                        break;
                    }

                    if (!deque.isEmpty() && deque.peekLast() > 0) {
                        deque.add(deque.pollLast() + num * 3);
                    } else {
                        deque.add(num * 3);
                    }
                } else {
                    isPossible = false;
                    break;
                }
            }
        }

        if (!isPossible || deque.size() != 1 || deque.peekLast() < 0) System.out.println(0);
        else System.out.println(deque.pollLast());
    }
}