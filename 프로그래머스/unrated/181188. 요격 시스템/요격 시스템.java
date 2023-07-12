import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            else return o1[1] - o2[1];
        });
        for (int i = 0; i < targets.length; i++) {
            pq.offer(targets[i]);
        }
        
        int answer = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            answer++;
            
            int end = cur[1];
            while (!pq.isEmpty()) {
                if (end <= pq.peek()[0]) break;
                end = Math.min(pq.peek()[1], end);
                pq.poll();
            }
        }

        return answer;
    }
}