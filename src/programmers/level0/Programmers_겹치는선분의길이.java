package programmers.level0;

import java.util.Arrays;

public class Programmers_겹치는선분의길이 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] lines = {{0, 1}, {2, 5}, {3, 9}};
        System.out.println(sol.solution(lines));

        int[][] lines2 = {{-1, 1}, {1, 3}, {3, 9}};
        System.out.println(sol.solution(lines2));

        int[][] lines3 = {{0, 5}, {3, 9}, {1, 10}};
        System.out.println(sol.solution(lines3));
    }

    static class Solution {
        public int solution(int[][] lines) {
            int[] line = new int[201];
            for (int i = 0; i < lines.length; i++) {
                int a = lines[i][0];
                int b = lines[i][1];
                for (int j = a + 100; j < b + 100; j++) {
                    line[j] += 1;
                }
            }
            return (int) Arrays.stream(line).filter(x -> x >= 2).count();
        }
    }
}
