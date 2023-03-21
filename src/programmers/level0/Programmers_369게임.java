package programmers.level0;

import java.util.Arrays;

public class Programmers_369게임 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(3));
        System.out.println(sol.solution(29423));
    }

    static class Solution {
        public int solution(int order) {
            int answer = 0;
            while (order > 0) {
                if(order % 10 == 3 || order % 10 == 6 || order % 10 == 9){
                    answer++;
                }
                order /= 10;
            }
            return answer;
        }
    }
}
