import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C;
    private static int[] me;
    private static char[][] board;

    private static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    private static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);

                if (board[i][j] == 'I') {
                    me = new int[]{i, j};
                }
            }
        }

        String operation = br.readLine();
        for (int i = 0; i < operation.length(); i++) {
            int d = operation.charAt(i) - '0';
            // 1. 종수 이동 + 게임 끝 검사
            boolean move = moveAndCheckEnd(d);
            if (!move) {
                System.out.println("kraj " + (i + 1));
                return;
            }

            // 2. 미친 아두이노 이동 + 게임 끝 검사 + 아두이노 파괴 검사
            boolean moveCrazy = moveCrazy();
            if (!moveCrazy) {
                System.out.println("kraj " + (i + 1));
                return;
            }
        }

        board[me[0]][me[1]] = 'I';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static boolean moveAndCheckEnd(int direction) {
        me[0] += dx[direction];
        me[1] += dy[direction];

        return board[me[0]][me[1]] != 'R';
    }

    private static boolean moveCrazy() {
        Queue<int[]> crazyQueue = getCrazyQueue();
        char[][] newBoard = new char[R][C];
        for (int i = 0; i < R; i++) Arrays.fill(newBoard[i], '.');

        boolean[][] visited = new boolean[R][C];
        while (!crazyQueue.isEmpty()) {
            int[] cur = crazyQueue.poll();

            nextPoint(cur);

            if (cur[0] == me[0] && cur[1] == me[1]) {
                return false;
            }

            if (visited[cur[0]][cur[1]]) {
                newBoard[cur[0]][cur[1]] = '.';
            } else {
                newBoard[cur[0]][cur[1]] = 'R';
                visited[cur[0]][cur[1]] = true;
            }
        }

        board = newBoard;
        return true;
    }

    private static void nextPoint(int[] cur) {
        if (cur[0] > me[0]) {
            cur[0] -= 1;
        } else if (cur[0] < me[0]) {
            cur[0] += 1;
        }

        if (cur[1] > me[1]) {
            cur[1] -= 1;
        } else if (cur[1] < me[1]) {
            cur[1] += 1;
        }
    }

    private static Queue<int[]> getCrazyQueue() {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'R') {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        return queue;
    }

}
