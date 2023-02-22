import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_최적경로 {
    static int N;
    static int[][] pos;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine()) + 2;
            pos = new int[N][2];
            result = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pos[i][0] = Integer.parseInt(st.nextToken());
                pos[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 3, 0);
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb.toString());
    }

    static void dfs(int idx, int flag, int sum) {
        if (flag + 1 == (1 << N)) {
            int cost = Math.abs(pos[idx][0] - pos[1][0]) + Math.abs(pos[idx][1] - pos[1][1]);
            result = Math.min(result, sum + cost);
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((flag & (1 << i)) != 0) continue;

            int cost = Math.abs(pos[idx][0] - pos[i][0]) + Math.abs(pos[idx][1] - pos[i][1]);
            dfs(i, flag | (1 << i), sum + cost);
        }
    }
}