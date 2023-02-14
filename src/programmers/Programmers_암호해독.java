package programmers;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Programmers_암호해독 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("dfjardstddetckdaccccdegk", 4));
        System.out.println(sol.solution("pfqallllabwaoclk", 2));
    }

    static class Solution {
        public String solution(String cipher, int code) {
            return IntStream.range(0, cipher.length()).mapToObj(x -> (x + 1) % code == 0 ? cipher.charAt(x) + "" : "")
                    .collect(Collectors.joining());
        }
    }
}
