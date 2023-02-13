package programmers;

import java.util.Arrays;

public class Programmers_n의배수고르기 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 3;
        int[] numList = {4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println(Arrays.toString(sol.solution(n, numList)));
    }

    static class Solution {
        public int[] solution(int n, int[] numlist) {
            return Arrays.stream(numlist).filter(x -> x % n == 0).toArray();
        }
    }
}
