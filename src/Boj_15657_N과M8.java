/*
    Boj 15657. N과 M(8)
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_15657_N과M8 {
    static int[] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] out = new int[M];
        sb = new StringBuilder();

        Arrays.sort(arr);

        nAndM(N, M, 0, out, 0);

        System.out.print(sb.toString());
    }

    static void nAndM(int n, int m, int depth, int[] out, int lastIdx) {
        if (depth == m) {
            for(int num : out){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i < lastIdx) {
                continue;
            }

            out[depth] = arr[i];
            nAndM(n, m, depth + 1, out, i);
        }
    }
}
