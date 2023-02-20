package programmers;

public class Programmers_삼각형의완성조건2 {
    public static void main(String[] args) {

    }

    static class Solution {
        public int solution(int[] sides) {
            return 2 * Math.min(sides[0], sides[1]) - 1;
        }
    }
}
