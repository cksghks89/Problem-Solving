package programmers.level0;

import java.util.StringTokenizer;

public class Programmers_문자열계산하기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("3 + 4"));
    }

    static class Solution {
        public int solution(String my_string) {
            StringTokenizer st = new StringTokenizer(my_string);
            int result = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                String op = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if(op.equals("+")) result += num;
                else if(op.equals("-")) result -= num;
            }

            return result;
        }
    }
}
