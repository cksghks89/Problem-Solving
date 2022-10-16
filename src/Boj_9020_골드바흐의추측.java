/*
    Boj 9020. 골드바흐의 추측
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_9020_골드바흐의추측 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        boolean[] prime = new boolean[10001];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;

        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) {
                for (int j = i + i; j < prime.length; j += i) {
                    prime[j] = false;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int test = 0; test < T; test++) {
            int n = Integer.parseInt(br.readLine());

            int prime1 = 0;
            int prime2 = 0;

            for (int i = 2; i <= n / 2; i++) {
                if (prime[i] && prime[n - i]) {
                    prime1 = i;
                    prime2 = n - i;
                }
            }
            sb.append(prime1 + " " + prime2+"\n");
        }

        System.out.print(sb.toString());
    }
}
