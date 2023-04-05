import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] out;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        out = new int[N];
        perm(0, 0);
        System.out.print(sb);
    }

    static void perm(int cnt, int visited) {
        if (cnt == N) {
            for (int i = 0; i < N; i++) {
                sb.append(out[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if ((visited & (1 << i)) != 0) continue;
            out[cnt] = i;
            perm(cnt + 1, visited | (1 << i));
        }
    }
}
