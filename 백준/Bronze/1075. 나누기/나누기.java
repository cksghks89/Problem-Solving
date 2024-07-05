import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] num = br.readLine().toCharArray();
        int F = Integer.parseInt(br.readLine());

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                num[num.length - 2] = (char) ('0' + i);
                num[num.length - 1] = (char) ('0' + j);

                int intNum = Integer.parseInt(String.valueOf(num));
                if (intNum % F == 0) {
                    System.out.println(i + "" + j);
                    return;
                }
            }
        }
    }
}
