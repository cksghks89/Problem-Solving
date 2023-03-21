package programmers.level0;

public class Programmers_편지 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("happy birthday!"));
    }
    static class Solution {
        public int solution(String message) {
            return message.length() * 2;
        }
    }
}
