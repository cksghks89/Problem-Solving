package programmers.level0;

public class Programmers_7의개수 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] array = {7, 77, 17};
        System.out.println(sol.solution(array));
    }

    static class Solution {
        public int solution(int[] array) {
            int answer = 0;
            for (int j : array) {
                answer += getSeven(j);
            }
            return answer;
        }

        static int getSeven(int num) {
            int cnt = 0;
            while (num > 0) {
                if (num % 10 == 7) cnt++;
                num /= 10;
            }
            return cnt;
        }
    }
}
