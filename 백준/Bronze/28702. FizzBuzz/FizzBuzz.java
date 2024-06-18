import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = new String[3];
        for (int i = 0; i < 3; i++) input[i] = br.readLine();
        int num = 0;
        for (int i = 3; i >= 1; i--) {
            String cur = input[3 - i];
            try {
                num = Integer.parseInt(cur) + i;
                break;
            } catch (Exception ignored) {
            }
        }

        if (num % 3 == 0 && num % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (num % 3 == 0) {
            System.out.println("Fizz");
        } else if (num % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(num);
        }
    }
}
