/*
    Boj 1654. 랜선 자르기
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1654_랜선자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] ran = new long[K];
        for (int i = 0; i < K; i++) {
            ran[i] = Integer.parseInt(br.readLine());
        }

        long maxResult = 0;

        long left = 1;
        long right = Arrays.stream(ran).max().orElse(0);
        while (left <= right) {
            long mid = (left + right) / 2;
            int cnt = getCnt(mid, ran);

            if (cnt >= N) {
                maxResult = Math.max(maxResult, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(maxResult);
    }

    static int getCnt(long mid, long[] ran) {
        return Arrays.stream(ran).map(x -> x / mid).mapToInt(x -> (int) x).sum();
    }
}
