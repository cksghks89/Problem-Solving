package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class SWEA_4012_요리사 {
    static int N;
    static int[][] map;
    static int result;
    static int[] out;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            out = new int[N / 2];
            result = Integer.MAX_VALUE;
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            comb(0, 0);

            sb.append(String.format("#%d %d\n", tc, result));
        }
        System.out.print(sb.toString());
    }

    static void comb(int cnt, int start) {
        if (cnt == N / 2) {
            // 선택된 재료가 A, 나머지 재료는 B가 된다.
            int[] A = out;
            int[] B = IntStream.range(0, N).filter(x -> Arrays.binarySearch(A, x) < 0).toArray();

            int flavorA = getFlavor(A);
            int flavorB = getFlavor(B);

            // 맛의 차이 절대값을 result 에
            result = Math.min(result, Math.abs(flavorA - flavorB));
            return;
        }

        for (int i = start; i < N; i++) {
            out[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    static int getFlavor(int[] select) {
        int flavor = 0;
        for (int i = 0; i < select.length; i++) {
            for (int j = 0; j < select.length; j++) {
                if (i == j) continue;
                flavor += map[select[i]][select[j]];
            }
        }
        return flavor;
    }
}
