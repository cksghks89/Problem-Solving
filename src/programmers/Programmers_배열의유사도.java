package programmers;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Programmers_배열의유사도 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] s1 = {"a", "b", "c"};
        String[] s2 = {"com", "b", "d", "p", "c"};
        System.out.println(sol.solution(s1, s2));
    }

    static class Solution {
        public int solution(String[] s1, String[] s2) {
            Set<String> set = Arrays.stream(s1).collect(Collectors.toSet());
            set.retainAll(Arrays.stream(s2).collect(Collectors.toSet()));
            return set.size();
        }
    }
}
