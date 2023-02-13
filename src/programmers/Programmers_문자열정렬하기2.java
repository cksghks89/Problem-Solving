package programmers;

import java.util.Arrays;

public class Programmers_문자열정렬하기2 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String my_string = "Bcad";
        System.out.println(sol.solution(my_string));
        String my_string2 = "heLLo";
        System.out.println(sol.solution(my_string2));
    }

    static class Solution {
        public String solution(String my_string) {
            StringBuilder sb = new StringBuilder();
            my_string = my_string.toLowerCase();
            Arrays.stream(my_string.split("")).sorted().forEach(sb::append);
            return sb.toString();
        }
    }
}
