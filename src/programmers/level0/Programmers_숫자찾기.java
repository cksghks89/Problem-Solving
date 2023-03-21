package programmers.level0;

public class Programmers_숫자찾기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(29183, 1));
        System.out.println(sol.solution(232443, 4));
        System.out.println(sol.solution(123456, 7));

    }

    static class Solution {
        public int solution(int num, int k) {
            int tmp = (num + "").indexOf(k + "");
            return tmp >= 0 ? tmp + 1 : -1;
        }
    }
}
