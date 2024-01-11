import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static long[][] P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        P = new long[3][2];

        for (int i = 0; i < 3; i++) {
            P[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }

        int result = ccw();
        System.out.println(result);
    }

    private static int ccw() {
        long result = (P[0][0] * P[1][1] + P[1][0] * P[2][1] + P[2][0] * P[0][1]) - (P[0][1] * P[1][0] + P[1][1] * P[2][0] + P[2][1] * P[0][0]);

        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
