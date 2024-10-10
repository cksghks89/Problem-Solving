import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static long[] S;
    private static long[] acc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            S = new long[N];
            acc = new long[N];

            for (int i = 0; i < N; i++) {
                S[i] = Long.parseLong(st.nextToken());
            }
            Arrays.sort(S);

            acc[0] = S[0];
            for (int i = 1; i < S.length; i++) {
                acc[i] = acc[i - 1] + S[i];
            }

            long curAnswer = 0;
            for (int i = 2; i <= S.length; i++) {
                curAnswer += getMinValue(i);
            }

            sb.append(curAnswer).append('\n');
        }

        System.out.println(sb);
    }

    private static long getMinValue(int l) {
        long result = S[l - 1] * l - acc[l - 1];

        for (int i = l; i < S.length; i++) {
            result = Math.min(result, S[i] * l - (acc[i] - acc[i - l]));
        }

        return result;
    }
}
