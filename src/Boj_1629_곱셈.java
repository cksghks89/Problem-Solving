/*
    Boj 1629. 곱셈
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj_1629_곱셈 {
    static long A;
    static long B;
    static long C;

    static Map<Long, Long> dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        dp = new HashMap<>();
        dp.put(1L, A);

        System.out.println(recursion(B));
    }

    static long recursion(long exponent) {
        if (exponent == 1) {
            return A % C;
        }

        if (dp.containsKey(exponent)) {
            return dp.get(exponent);
        }

        if (exponent % 2 == 0) {
            long rtn = (recursion(exponent / 2) * recursion(exponent / 2)) % C;
            dp.put(exponent, rtn);
        } else {
            long rtn = (recursion(exponent / 2) * recursion(exponent / 2 + 1)) % C;
            dp.put(exponent, rtn);
        }

        return dp.get(exponent);
    }
}
