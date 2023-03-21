/*
    Boj 2011. 암호코드
    level. gold 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2011_암호코드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int[] code = new int[input.length + 2];
        for (int i = 0; i < input.length; i++) {
            code[i + 2] = input[i] - '0';
        }

        int[] dp = new int[input.length + 2];

        // 1 ~ 26 이 각 문자
        // dp[i] => i 인덱스 까지의 가능한 모든 경우의 수
        dp[1] = 1;
        dp[2] = 1;
        if (code[2] == 0) {
            System.out.println(0);
            return;
        }
        for (int i = 3; i < dp.length; i++) {
            if (code[i] == 0 && (code[i - 1] == 1 || code[i - 1] == 2)) {
                dp[i] = dp[i - 2];
            } else if (code[i] != 0 && (code[i - 1] == 1 || (code[i - 1] == 2 && 0 <= code[i] && code[i] <= 6))) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
            } else if (1 <= code[i] && code[i] <= 9) {
                dp[i] = dp[i - 1];
            } else {
                System.out.println(0);
                return;
            }
        }

        System.out.println(dp[dp.length - 1]);
    }
}
