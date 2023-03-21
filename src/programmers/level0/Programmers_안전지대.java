package programmers.level0;

public class Programmers_안전지대 {
    public static void main(String[] args) {

    }

    static class Solution {
        int[][] dangerMap;
        public int solution(int[][] board) {
            int answer = 0;

            dangerMap = new int[board.length][board.length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 1) {
                        setDanger(i, j, board.length, board);
                    }
                }
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if(dangerMap[i][j] == 0) answer += 1;
                }
            }

            return answer;
        }

        void setDanger(int x, int y, int n, int[][] board) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (isInRange(i - 1 + x, j - 1 + y, n)) {
                        dangerMap[i - 1 + x][j - 1 + y] = 1;
                    }
                }
            }
        }

        boolean isInRange(int x, int y, int n) {
            return (0 <= x && x < n) && (0 <= y && y < n);
        }
    }
}
