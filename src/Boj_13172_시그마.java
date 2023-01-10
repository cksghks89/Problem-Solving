/*
    Boj 13172. 시그마
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_13172_시그마 {
    static final int PRIME = 1_000_000_007;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        long sum = 0;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long ni = Integer.parseInt(st.nextToken());
            long si = Integer.parseInt(st.nextToken());

            sum += (si * zegob(ni, PRIME-2)) % PRIME;
            sum %= PRIME;
        }

        System.out.println(sum);
    }

    /**
     * num 의 cnt 제곱
     */
    static long zegob(long num, long cnt) {
        if (cnt == 1) {
            return num % PRIME;
        }

        if(cnt % 2 == 1){
            return (num * (zegob(num, cnt - 1) % PRIME)) % PRIME;
        }

        long cur = zegob(num, cnt / 2);
        return (cur * cur) % PRIME;
    }
}
