package programmers;

public class Programmers_저주의숫자3 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(15));
        System.out.println(sol.solution(40));
    }

    static class Solution {
        public int solution(int n) {
            int _3x = 1;
            int idx = 1;
            while (idx != n) {
                idx += 1;
                _3x += 1;
                while (isThree(_3x)) {
                    _3x += 1;
                }
            }

            return _3x;
        }

        boolean isThree(int num) {
            if(num % 3 == 0) return true;

            while(num > 0){
                if(num % 10 == 3) return true;
                num /= 10;
            }
            return false;
        }
    }
}
