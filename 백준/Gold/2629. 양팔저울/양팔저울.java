import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] weights;
    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new boolean[31][40001];

        subSet(0, 0);

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j < 40001; j++) {
                if (dp[i][j]) set.add(j);
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (set.contains(cur)) System.out.print("Y ");
            else System.out.print("N ");
        }
    }

    private static void subSet(int cnt, int weight) {
        if (dp[cnt][weight]) return;
        dp[cnt][weight] = true;

        if (cnt == N) return;

        subSet(cnt + 1, weight + weights[cnt]);
        subSet(cnt + 1, weight);
        subSet(cnt + 1, Math.abs(weight - weights[cnt]));
    }
}
