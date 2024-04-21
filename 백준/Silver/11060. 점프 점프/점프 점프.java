import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int INF = 11_000_000;

        int[] memo = new int[1101];
        for (int i = 1; i <= 1100; i++) memo[i] = INF;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= i + A[i]; j++) {
                memo[j] = Math.min(memo[j], memo[i] + 1);
            }
        }

        System.out.println(memo[N - 1] == INF ? -1 : memo[N - 1]);
    }
}
