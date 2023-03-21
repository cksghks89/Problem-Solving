package programmers.level0;

public class Programmers_k의개수 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(1, 13, 1));
        System.out.println(sol.solution(10, 50, 5));
        System.out.println(sol.solution(3, 10, 2));
    }

    static class Solution {
        public int solution(int i, int j, int k) {
            int answer = 0;
            for (int x = i; x <= j; x++) {
                int cur = x;
                while (cur > 0) {
                    if (cur % 10 == k) {
                        answer++;
                    }
                    cur /= 10;
                }
            }
            return answer;
        }
    }
}
