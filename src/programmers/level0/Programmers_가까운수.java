package programmers.level0;

import java.util.Arrays;

public class Programmers_가까운수 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{3, 10, 28}, 20));
    }

    static class Solution {
        public int solution(int[] array, int n) {
            int answer = 0;
            Arrays.sort(array);
            int diff = Integer.MAX_VALUE;
            for (int i = 0; i < array.length; i++) {
                if (diff > Math.abs(n - array[i])) {
                    diff = Math.abs(n - array[i]);
                    answer = array[i];
                }
            }
            return answer;
        }
    }
}
