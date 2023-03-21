package programmers.level0;

public class Programmers_이진수더하기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("10", "11"));
        System.out.println(sol.solution("1001", "1111"));
    }

    static class Solution {
        public String solution(String bin1, String bin2) {
            String answer = "";
            int sum = binaryToDecimal(bin1) + binaryToDecimal(bin2);
            return Integer.toBinaryString(sum);
        }

        int binaryToDecimal(String bin) {
            int sum = 0;
            int idx = 0;
            for (int i = bin.length() - 1; i >= 0; i--) {
                if (bin.charAt(i) == '1') {
                    sum += Math.pow(2, idx);
                }
                idx++;
            }
            return sum;
        }
    }
}
