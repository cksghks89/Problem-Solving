import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Queue<String> input = new ArrayDeque<>();
        List<String> seq = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] cur = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                input.add(cur[j]);
                seq.add(cur[j]);
            }
        }

        Collections.sort(seq, (o1, o2) -> {
            if (o1.charAt(0) != o2.charAt(0)) return o1.compareTo(o2);
            int o1Num = Integer.parseInt(o1.split("-")[1]);
            int o2Num = Integer.parseInt(o2.split("-")[1]);
            return o1Num - o2Num;
        });

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < seq.size(); i++) {
            String cur = seq.get(i);

            while (true) {
                if (!stack.isEmpty() && stack.peek().equals(cur)) {
                    stack.pop();
                    break;
                } else if (!input.isEmpty() && input.peek().equals(cur)) {
                    input.poll();
                    break;
                } else if (!input.isEmpty() && !input.peek().equals(cur)) {
                    stack.push(input.poll());
                }

                if (input.isEmpty()) {
                    System.out.println("BAD");
                    return;
                }
            }
        }

        System.out.println("GOOD");
    }
}
