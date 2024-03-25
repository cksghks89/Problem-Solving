import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        if (arr[0] != 1) {
            System.out.println(1);
            return;
        }

        int[] acc = new int[arr.length];
        acc[0] = arr[0];

        int max = 1;
        for (int i = 1; i < N; i++) {
            acc[i] = acc[i - 1] + arr[i];

            if (acc[i - 1] + 2 <= arr[i]) {
                System.out.println(acc[i - 1] + 1);
                return;
            } else {
                max = acc[i];
            }
        }

        System.out.println(max + 1);
    }
}
