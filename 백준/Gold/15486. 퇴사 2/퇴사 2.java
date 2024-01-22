import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static List<int[]>[] TP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        TP = new List[N + 100];
        for (int i = 0; i < N + 100; i++) {
            TP[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            TP[i + t - 1].add(new int[]{t, p});
        }

        int[] dp = new int[N + 100];
        for (int i = 1; i < TP.length; i++) {
            List<int[]> tp = TP[i];
            dp[i] = dp[i - 1];

            for (int j = 0; j < tp.size(); j++) {
                int[] cur = tp.get(j);
                if (i - cur[0] < 0) {
                    dp[i] = Math.max(dp[i], cur[1]);
                } else {
                    dp[i] = Math.max(dp[i], dp[i - cur[0]] + cur[1]);
                }
            }
        }

        System.out.println(dp[N]);
    }
}
