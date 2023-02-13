/*
    Boj 1509. 팰린드롬 분할
    level. gold 1
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_1509_팰린드롬분할 {
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = " " + br.readLine();
        System.out.println(dp());
    }

    static int dp() {
        int[] dp = new int[str.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < str.length(); i++) {
            for (int j = 1; j <= i; j++) {
                if (!isPalindrome(j, i)) continue;
                dp[i] = Math.min(dp[i], dp[j - 1] + 1);
            }
        }

        return dp[str.length() - 1];
    }

    static boolean isPalindrome(int start, int end) {
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
