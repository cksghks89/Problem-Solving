/*
    Boj 1439. 뒤집기
    level. silver 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1439_뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        int zero = 0;
        int one = 0;

        int idx = 0;
        while (idx < S.length()) {
            char cur = S.charAt(idx);
            if (cur == '0') {
                zero += 1;
            } else {
                one += 1;
            }

            while (idx < S.length()) {
                if (cur != S.charAt(idx)) {
                    break;
                }
                idx += 1;
            }
        }

        System.out.println(Math.min(zero, one));
    }
}
