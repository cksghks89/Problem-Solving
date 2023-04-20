import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int len = queue1.length + queue2.length;
        long q1Sum = 0, q2Sum = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        for (int n : queue1) {
            q1Sum += n;
            q1.offer(n);
        }
        for (int n : queue2) {
            q2Sum += n;
            q2.offer(n);
        }
        if ((q1Sum + q2Sum) % 2 == 1) return -1;
        long half = (q1Sum + q2Sum) / 2;

        int cnt = 0;
        Queue<Integer> copyQ1 = new ArrayDeque<>(q1);
        Queue<Integer> copyQ2 = new ArrayDeque<>(q2);
        while (cnt < 2*len) {
            if (q1Sum == half) break;

            if (q1Sum < half) {
                int cur = copyQ2.poll();
                copyQ1.offer(cur);
                q1Sum += cur;
            } else {
                int cur = copyQ1.poll();
                copyQ2.offer(cur);
                q1Sum -= cur;
            }
            cnt++;
        }

        int cnt2 = 0;
        while (cnt2 < 2*len) {
            if (q2Sum == half) break;

            if (q2Sum < half) {
                int cur = q1.poll();
                q2.offer(cur);
                q2Sum += cur;
            } else {
                int cur = q2.poll();
                q1.offer(cur);
                q2Sum -= cur;
            }
            cnt2++;
        }

        if (cnt == 2*len && cnt2 == 2*len) {
            return -1;
        }

        return Math.min(cnt, cnt2);
    }
}