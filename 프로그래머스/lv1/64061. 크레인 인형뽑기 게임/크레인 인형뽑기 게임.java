import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        Queue<Integer>[] queues = new Queue[board.length];
        for (int i = 0; i < board.length; i++) {
            queues[i] = new ArrayDeque<>();
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][i] == 0) continue;
                queues[i].offer(board[j][i]);
            }
        }

        Stack<Integer> basket = new Stack<>();
        int answer = 0;

        for (int i = 0; i < moves.length; i++) {
            int loc = moves[i] - 1;
            if (queues[loc].isEmpty()) continue;

            int select = queues[loc].poll();
            if (!basket.isEmpty() && basket.peek() == select) {
                answer += 2;
                basket.pop();
            } else {
                basket.push(select);
            }
        }

        return answer;
    }
}