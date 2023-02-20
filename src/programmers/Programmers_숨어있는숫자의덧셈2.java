package programmers;

import java.util.Arrays;

public class Programmers_숨어있는숫자의덧셈2 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution("aAb1B2cC34oOp"));
    }

    static class Solution {
        public int solution(String my_string) {
            return Arrays.stream(my_string.split("[^0-9]")).filter(x -> x.length() != 0).mapToInt(Integer::parseInt).sum();
        }
    }
}
