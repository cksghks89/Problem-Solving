package programmers;

public class Programmers_최댓값만들기2 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] numbers = {1, 2, -3, 4, -5};
        System.out.println(sol.solution(numbers));
    }

    static class Solution {
        public int solution(int[] numbers) {
            int answer = Integer.MIN_VALUE;

            for (int i = 0; i < numbers.length - 1; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    answer = Math.max(answer, numbers[i] * numbers[j]);
                }
            }
            return answer;
        }
    }
}
