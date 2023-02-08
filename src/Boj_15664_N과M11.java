/*
    Boj 15665. N과 M(11)
    level. silver 2
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj_15664_N과M11 {
    static Set<String> set;
    static int N, M;
    static int[] out, inputs;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted().toArray();
        out = new int[M];
        sb = new StringBuilder();
        set = new HashSet<>();

        comb(0);
        System.out.print(sb.toString());
    }

    static void comb(int cnt) {
        if (cnt == M) {
            if (set.contains(Arrays.toString(out))) {
                return;
            }

            for (int n : out) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            set.add(Arrays.toString(out));
            return;
        }

        for (int i = 0; i < N; i++) {
            out[cnt] = inputs[i];
            comb(cnt + 1);
        }
    }
}
