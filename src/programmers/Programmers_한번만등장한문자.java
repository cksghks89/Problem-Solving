package programmers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Programmers_한번만등장한문자 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("abcabcadc"));
        System.out.println(sol.solution("abdc"));
        System.out.println(sol.solution("hello"));
    }

    static class Solution {
        public String solution(String s) {
            return Arrays.stream(s.split(""))
                    .sorted()
                    .filter(x -> s.indexOf(x) == s.lastIndexOf(x))
                    .collect(Collectors.joining());
        }
    }
}
