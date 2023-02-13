package programmers;

public class Programmers_제곱수판별하기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(144));
        System.out.println(sol.solution(976));
    }
    static class Solution {
        public int solution(int n) {
            return (int)Math.pow((int)Math.sqrt(n), 2) == n ? 1 : 2;
        }
    }
}
