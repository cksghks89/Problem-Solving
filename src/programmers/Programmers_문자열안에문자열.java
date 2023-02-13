package programmers;

public class Programmers_문자열안에문자열 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("ab6CDE443fgh22iJKlmn1o", "6CD"));
    }

    static class Solution {
        public int solution(String str1, String str2) {
            return str1.contains(str2) ? 1 : 2;
        }
    }
}
