/*
    Boj 2166. 다각형의 면적
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2166_다각형의면적 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long[] x = new long[N + 1];
        long[] y = new long[N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }
        x[N] = x[0];
        y[N] = y[0];

        long sumX = 0;
        long sumY = 0;

        for (int i = 0; i < N; i++) {
            sumX += x[i] * y[i + 1];
            sumY += x[i + 1] * y[i];
        }

        System.out.printf("%.1f", Math.abs(sumX - sumY) / 2.0);
    }
}
