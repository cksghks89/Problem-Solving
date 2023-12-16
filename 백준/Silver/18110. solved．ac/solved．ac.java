import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] opinion = new int[n];

        for (int i = 0; i < n; i++) {
            opinion[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(opinion);

        int remove = (int) Math.round(n * 0.15);

        int sum = 0;
        for (int i = remove; i < n - remove; i++) {
            sum += opinion[i];
        }

        System.out.println(Math.round(1.0 * sum / (n - 2 * remove)));
    }
}
