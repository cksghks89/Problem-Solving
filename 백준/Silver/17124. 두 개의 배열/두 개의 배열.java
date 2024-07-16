import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] A;
    private static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Arrays.sort(B);

            long sum = 0;
            for (int i = 0; i < n; i++) {
                int idx = Arrays.binarySearch(B, A[i]);

                if (idx >= 0) {
                    sum += B[idx];
                } else {
                    idx = -idx - 1;

                    int a = idx - 1 >= 0 ? Math.abs(A[i] - B[idx - 1]) : Integer.MAX_VALUE;
                    int b = idx < B.length ? Math.abs(A[i] - B[idx]) : Integer.MAX_VALUE;
                    int c = idx + 1 < B.length ? Math.abs(A[i] - B[idx + 1]) : Integer.MAX_VALUE;

                    if (a <= b && a <= c) sum += B[idx - 1];
                    else if (b <= a && b <= c) sum += B[idx];
                    else if (c <= a && c <= b) sum += B[idx + 1];
                }
            }

            sb.append(sum).append('\n');
        }

        System.out.println(sb);
    }
}
