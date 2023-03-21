package programmers.level2;

public class Programmers_멀쩡한사각형 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int w = 8;
        int h = 12;
        System.out.println(sol.solution(w, h));

        w = 3;
        h = 11;
        System.out.println(sol.solution(w, h));
    }

    static class Solution {
        public long solution(int w, int h) {
            long all = 1;
            all *= w;
            all *= h;

            // w < h 가 되도록 스왑
            if (w > h) {
                int temp = w;
                w = h;
                h = temp;
            }

            long repeat = 1;    // 반복 패턴의 횟수

            while (true) {
                int cur = gcd(w, h);
                if (cur == 1) break;

                repeat *= cur;  // 모든 최대공약수의 곱 = 반복패턴의 횟수

                w /= cur;   // w, h가 서로소가 되도록 최대공약수로 계속 나눔
                h /= cur;
            }

            // 각 패턴에서 흰 정사각형 개수 구하기
            long num = h + (w - 1);

            return all - repeat * num;
        }
        // 최대공약수
        public int gcd(int small, int big) {
            if (big % small == 0) return small;
            return gcd(big % small, small);
        }
    }
}
