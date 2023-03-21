package programmers.level0;

import java.util.Arrays;

public class Programmers_삼각형의완성조건 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{1, 2, 3}));
        System.out.println(sol.solution(new int[]{3, 6, 2}));
        System.out.println(sol.solution(new int[]{199, 72, 222}));
    }

    static class Solution {
        public int solution(int[] sides) {
            Arrays.sort(sides);
            return sides[2] < sides[0] + sides[1] ? 1 : 2;
        }
    }
}
