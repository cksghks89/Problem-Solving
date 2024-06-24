import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final long MOD = 1_000_000_007;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long A = Long.parseLong(st.nextToken());

        int H = Integer.parseInt(br.readLine());

        long answer = 1;
        for (int i = 0; i < N - 1; i++) {
            answer *= M;
            answer = answer % MOD;
        }

        System.out.println(answer);
    }
}
