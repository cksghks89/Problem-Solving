package programmers.level0;

public class Programmers_자릿수더하기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(1234));
        System.out.println(sol.solution(930211));
    }

    static class Solution {
        public int solution(int n) {
            int answer = 0;
            while (n > 0) {
                answer += n % 10;
                n /= 10;
            }
            return answer;
        }
    }
}
