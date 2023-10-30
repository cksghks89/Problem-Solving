import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int count = 0;
        int num = 666;
        while (true) {
            CharSequence cs = "666";
            if (String.valueOf(num).contains(cs)) {
                count += 1;
            }

            if (count == N) {
                System.out.println(num);
                return;
            }
            num++;
        }
    }
}
