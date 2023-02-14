package programmers;

public class Programmers_영어가싫어요 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("onetwothreefourfivesixseveneightnine"));
        System.out.println(sol.solution("onefourzerosixseven"));
    }

    static class Solution {
        public long solution(String numbers) {
            String[] enNum = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
            for (int i = 0; i < 10; i++) {
                numbers = numbers.replaceAll(enNum[i], i + "");
            }
            return Long.parseLong(numbers);
        }
    }
}
