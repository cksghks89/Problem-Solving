package programmers.level0;

public class Programmers_치킨쿠폰 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(100));
        System.out.println(sol.solution(1081));
    }

    static class Solution {
        public int solution(int chicken) {
            int answer = 0;
            while (chicken >= 10) {
                int cur = chicken / 10;
                answer += cur;

                cur += chicken % 10;
                chicken = cur;
            }
            return answer;
        }
    }
}
