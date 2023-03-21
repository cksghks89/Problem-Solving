package programmers.level0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Programmers_잘라서배열로저장하기 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String my_str = "abcdef123";
        System.out.println(Arrays.toString(sol.solution(my_str, 3)));
    }

    static class Solution {
        public String[] solution(String my_str, int n) {
            List<String> list = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < my_str.length(); i++) {
                sb.append(my_str.charAt(i));
                if ((i + 1) % n == 0) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
            if (sb.length() > 0) {
                list.add(sb.toString());
            }

            return list.toArray(new String[0]);
        }
    }
}
