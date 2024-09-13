import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();

        String firstOp = null;
        while ((firstOp = br.readLine()) != null) {
            int[] memory = new int[32];
            int acc = 0;
            int pc = 0;

            memory[0] = Integer.parseInt(firstOp, 2);
            for (int i = 1; i < 32; i++) {
                memory[i] = Integer.parseInt(br.readLine(), 2);
            }

            while (true) {
                int cur = memory[pc];
                pc = (pc + 1) % 32;

                int op = cur / 32;
                int operand = cur % 32;

                if (op == 0) {
                    memory[operand] = acc;
                } else if (op == 1) {
                    acc = memory[operand];
                } else if (op == 2 && acc == 0) {
                    pc = operand;
                } else if (op == 4) {
                    acc = (acc + 255) % 256;
                } else if (op == 5) {
                    acc = (acc + 1) % 256;
                } else if (op == 6) {
                    pc = operand;
                } else if (op == 7) {
                    for (int i = 7; i >= 0; i--) {
                        answer.append((acc >> i) & 1);
                    }
                    answer.append('\n');
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
