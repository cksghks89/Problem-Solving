package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_3234_준환이의양팔저울 {
    static int N;
    static int[] arr;
    static boolean[] visited;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            visited = new boolean[N];
            result = 0;

            int[] out = new int[N];
            permutation(N, 0, out);

            sb.append("#"+tc+" ").append(result).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void permutation(int n, int depth, int[] out) {
        if (depth == n) {
            result += getPossibleCnt(0, 0, 0, out);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                permutation(n, depth + 1, out);
                visited[i] = false;
            }
        }
    }

    static int getPossibleCnt(int left, int right, int idx, int[] out) {
        if (left < right) {
            return 0;
        } else if (idx == out.length) {
            return 1;
        }

        int leftCnt = getPossibleCnt(left + out[idx], right, idx + 1, out);
        int rightCnt = getPossibleCnt(left, right + out[idx], idx + 1, out);

        return leftCnt + rightCnt;
    }
}
