package programmers;

public class Programmers_세균증식 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(2, 10));
        System.out.println(sol.solution(7, 15));
        System.out.println(sol.solution(10, 15));
    }

    static class Solution {
        public int solution(int n, int t) {
            return n * (int)Math.pow(2, t);
        }
    }
}
