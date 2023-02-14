package programmers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Programmers_대문자와소문자 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("cccCCC"));
    }

    static class Solution {
        public String solution(String my_string) {
            StringBuilder answer = new StringBuilder();
            List<Character> list = Arrays.stream(my_string.split(""))
                    .map(x -> x.charAt(0)).collect(Collectors.toList());
            for (char ch : list) {
                if ('A' <= ch && ch <= 'Z') {
                    answer.append((char) (ch + 32));
                } else {
                    answer.append((char) (ch - 32));
                }
            }
            return answer.toString();
        }
    }
}
