import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int end = 1;
            for (int j = 0; j < input[1]; j++) {
                end = (end * input[0]) % 10;
            }
            sb.append(end == 0 ? 10 : end).append('\n');
        }
        System.out.println(sb);
    }
}
