package programmers;

import java.util.stream.IntStream;

public class Programmers_평행 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] dots = {{1, 4}, {9, 2}, {3, 8}, {11, 6}};
        System.out.println(sol.solution(dots));

        int[][] dots2 = {{3, 5}, {4, 1}, {2, 4}, {5, 10}};
        System.out.println(sol.solution(dots2));
    }

    static class Solution {
        int result;
        public int solution(int[][] dots) {
            result = 0;
            comb(0, 0, new int[4], dots);
            return result;
        }

        void comb(int cnt, int start, int[] out, int[][] dots) {
            if (cnt == 2) {
                int[] other = IntStream.range(0, 4).filter(x -> x != out[0] && x != out[1]).toArray();
                int left = (dots[out[0]][0] - dots[out[1]][0]) * (dots[other[0]][1] - dots[other[1]][1]);
                int right = (dots[out[0]][1] - dots[out[1]][1]) * (dots[other[0]][0] - dots[other[1]][0]);
                if(left == right) result = 1;
                return;
            }
            for (int i = start; i < 4; i++) {
                out[cnt] = i;
                comb(cnt + 1, i + 1, out, dots);
            }
        }
    }
}
