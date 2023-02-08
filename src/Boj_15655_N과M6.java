/*
    Boj 15655. N과 M (6)
    level. silver 3
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_15655_N과M6 {
    static int N, M;
    static int[] out;
    static int[] inputs;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        out = new int[M];
        sb = new StringBuilder();

        comb(0, 0);

        System.out.print(sb.toString());
    }

    static void comb(int cnt, int start) {
        if (cnt == M) {
            for (int n : out) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N - (M - cnt - 1); i++) {
            out[cnt] = inputs[i];
            comb(cnt + 1, i + 1);
        }
    }
}
