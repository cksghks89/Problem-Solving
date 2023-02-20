package programmers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Programmers_외계어사전 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] spell = {"p", "o", "s"};
        String[] dic = {"sod", "eocd", "qixm", "adio", "soo"};
        System.out.println(sol.solution(spell, dic));
    }

    static class Solution {
        public int solution(String[] spell, String[] dic) {
            String word = Arrays.stream(spell).sorted().collect(Collectors.joining());

            for (String s : dic) {
                String cur = Arrays.stream(s.split("")).sorted().collect(Collectors.joining());
                if (cur.equals(word)) return 1;
            }
            return 2;
        }
    }
}
