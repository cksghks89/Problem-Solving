package programmers;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Programmers_OX퀴즈 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] quiz = {"3 - 4 = -3", "5 + 6 = 11"};
        System.out.println(Arrays.toString(sol.solution(quiz)));
    }

    static class Solution {
        public String[] solution(String[] quiz) {
            String[] answer = new String[quiz.length];

            for (int i = 0; i < quiz.length; i++) {
                StringTokenizer st = new StringTokenizer(quiz[i]);

                int x = Integer.parseInt(st.nextToken());
                String op = st.nextToken();
                int y = Integer.parseInt(st.nextToken());
                st.nextToken();
                int z = Integer.parseInt(st.nextToken());

                if (op.equals("+") && (x + y == z)) {
                    answer[i] = "O";
                } else if (op.equals("-") && (x - y == z)) {
                    answer[i] = "O";
                } else {
                    answer[i] = "X";
                }
            }

            return answer;
        }
    }
}
