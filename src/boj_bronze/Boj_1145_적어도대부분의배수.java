import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_1145_적어도대부분의배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int min = arr[0] * arr[1] * arr[2];
        for (int i = min; i >= arr[1]; i--) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (i % arr[j] == 0) {
                    cnt++;
                }
            }
            if (cnt >= 3) {
                min = i;
            }
        }
        System.out.println(min);
    }
}
