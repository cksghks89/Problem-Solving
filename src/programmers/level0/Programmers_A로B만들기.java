package programmers.level0;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Programmers_A로B만들기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("olleh", "hello"));
        System.out.println(sol.solution("allpe", "apple"));
    }

    static class Solution {
        public int solution(String before, String after) {
            return Arrays.stream(before.split("")).sorted().collect(Collectors.joining())
                    .equals(Arrays.stream(after.split("")).sorted().collect(Collectors.joining())) ? 1 : 0;
        }
    }
}
