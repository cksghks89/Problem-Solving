/*
    Boj 1509. 팰린드롬 분할
    level. gold 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj_1509_팰린드롬분할 {
    static ArrayList<int[]> palindrome;
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = " " + br.readLine();

        palindrome = new ArrayList<>();
        setAllPalindrome();

        palindrome.sort((o1, o2) -> o1[1] - o2[1]);

        System.out.println(dp());
    }

    static int dp() {
        int[] dp = new int[str.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int[] cur : palindrome) {
            int s = cur[0];
            int e = cur[1];

            dp[e] = Math.min(dp[e], dp[s - 1] + 1);
        }

        return dp[str.length() - 1];
    }

    static void setAllPalindrome() {
        for (int i = 1; i < str.length(); i++) {
            int idx = str.length() - 1;
            for (int j = idx; j >= i; j--) {
                if (isPalindrome(i, j)) {
                    palindrome.add(new int[]{i, j});
                }
            }
        }
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
