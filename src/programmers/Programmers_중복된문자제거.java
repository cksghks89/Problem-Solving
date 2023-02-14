package programmers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Programmers_중복된문자제거 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("people"));
        System.out.println(sol.solution("We are the world"));
    }

    static class Solution {
        public String solution(String my_string) {
            return Arrays.stream(my_string.split("")).distinct().collect(Collectors.joining());
        }
    }
}
