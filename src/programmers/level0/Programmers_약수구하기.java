package programmers.level0;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Programmers_약수구하기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(24)));
        System.out.println(Arrays.toString(sol.solution(29)));

    }

    static class Solution {
        public int[] solution(int n) {
            return IntStream.rangeClosed(1, n).filter(x -> n % x == 0).toArray();
        }
    }
}
