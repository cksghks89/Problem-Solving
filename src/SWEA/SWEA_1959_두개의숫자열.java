package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1959_두개의숫자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] A = Arrays.stream(br.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] B = Arrays.stream(br.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int max = N < M ? getMaxValue(A, B) : getMaxValue(B, A);

            sb.append("#"+tc+" ").append(max).append("\n");
        }
        System.out.print(sb.toString());
    }

    static int getMaxValue(int[] A, int[] B) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < B.length - A.length + 1; i++) {
            int sum = 0;
            for (int j = 0; j < A.length; j++) {
                sum += A[j] * B[i + j];
            }

            result = Math.max(result, sum);
        }

        return result;
    }
}
