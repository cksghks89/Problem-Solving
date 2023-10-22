import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] out;
    private static StringBuffer sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        out = new int[M];
        sb = new StringBuffer();

        comb(0, 1);
        System.out.println(sb);
    }

    private static void comb(int idx, int s) {
        if (idx == M) {
            for (int i = 0; i < M; i++) {
                sb.append(out[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = s; i <= N; i++) {
            out[idx] = i;
            comb(idx + 1, i + 1);
        }
    }
}
