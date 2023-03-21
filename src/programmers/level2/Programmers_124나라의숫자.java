package programmers.level2;

public class Programmers_124나라의숫자 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 10;
        System.out.println(sol.solution(n));
    }

    static class Solution {
        public String solution(int n) {
            int[] map = {1, 2, 4};

            int idx = 1;
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                int cur = (int) Math.pow(3, idx);
                if (n > cur) {
                    sb.append(map[((n - 1) % cur) / (cur / 3)]);
                } else {
                    sb.append(map[(n - 1) / (cur / 3)]);
                }
                n -= cur;
                idx++;
            }

            return sb.reverse().toString();
        }
    }
}
