import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String input = null;
        while ((input = br.readLine()) != null) {
            int x = Integer.parseInt(input) * 10000000;
            int n = Integer.parseInt(br.readLine());

            long[] arr = new long[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(br.readLine());
            }

            Arrays.sort(arr);

            int p1 = 0;
            int p2 = n - 1;
            boolean done = false;
            while (p1 < p2) {
                long sum = arr[p1] + arr[p2];
                if (sum == x) {
                    sb.append("yes ").append(arr[p1]).append(" ").append(arr[p2]).append('\n');
                    done = true;
                    break;
                } else if (sum < x) {
                    p1++;
                } else {
                    p2--;
                }
            }
            
            if (!done) {
                sb.append("danger").append('\n');
            }
        }

        System.out.println(sb);
    }
}
