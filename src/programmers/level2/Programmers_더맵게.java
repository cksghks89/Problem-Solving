import java.util.PriorityQueue;

public class Programmers_더맵게 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;

        System.out.println(sol.solution(scoville, K));

    }

    static class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < scoville.length; i++) {
                pq.offer(scoville[i]);
            }

            while (!pq.isEmpty()) {
                int low1 = pq.poll();
                if (low1 >= K) {
                    return answer;
                } else if (pq.isEmpty()) {
                    return -1;
                }

                answer += 1;
                int low2 = pq.poll();
                pq.offer(low1 + low2 * 2);
            }

            return answer;
        }
    }
}
