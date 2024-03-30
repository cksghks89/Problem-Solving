import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, L, K;
    private static int[][] star;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        star = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            star[i][0] = Integer.parseInt(st.nextToken());
            star[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int x = star[i][0];
                int y = star[j][1];

                int cnt = getCnt(x, y);
                answer = Math.min(answer, K - cnt);
            }
        }

        System.out.println(answer);
    }

    private static int getCnt(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            int[] cur = star[i];
            if (x <= cur[0] && cur[0] <= x + L && y <= cur[1] && cur[1] <= y + L) {
                cnt += 1;
            }
        }
        return cnt;
    }
}
