import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static int[][] board;

    // 0 : 동, 1 : 남, 2 : 서, 3 : 북
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        // 1. 주사위 상태 기록 + 상태 변경 로직
        // 2. 방향 변수 + 값 비교 후 방향 변경 로직
        // 3. BFS + 점수 계산 로직

        // 입력 및 초기화 ------------- start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 및 초기화 ------------- end

        // 이동 & 점수 계산 로직 ------------ start
        int answer = 0;

        Dice dice = new Dice();
        int direction = 0;  // 동쪽
        int x = 0, y = 0;
        for (int i = 0; i < K; i++) {
            if (!isInRange(x + dx[direction], y + dy[direction])) {
                direction = (direction + 2) % 4;
            }
            x += dx[direction];
            y += dy[direction];

            dice.rolling(direction);
            answer += getScore(x, y, board[x][y]);

            if (dice.bottom > board[x][y]) {
                direction = (direction + 1) % 4;
            } else if (dice.bottom < board[x][y]) {
                direction = (direction - 1 + 4) % 4;
            }
        }
        // 이동 & 점수 계산 로직 ------------ end

        // 출력부
        System.out.println(answer);
    }

    private static int getScore(int x, int y, int point) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        boolean[][] visited = new boolean[N][M];

        int count = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            count += 1;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (isInRange(nx, ny) && board[nx][ny] == point && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return point * count;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static class Dice {
        int up;
        int bottom;
        int front;
        int back;
        int left;
        int right;

        public Dice() {
            this.up = 1;
            this.bottom = 6;
            this.left = 4;
            this.right = 3;
            this.front = 5;
            this.back = 2;
        }

        public void rolling(int direction) {
            if (direction == 0) {
                rollEast();
            } else if (direction == 1) {
                rollSouth();
            } else if (direction == 2) {
                rollWest();
            } else if (direction == 3) {
                rollNorth();
            }
        }

        public void rollEast() {
            int temp = this.up;
            this.up = this.left;
            this.left = this.bottom;
            this.bottom = this.right;
            this.right = temp;
        }

        public void rollSouth() {
            int temp = this.up;
            this.up = this.back;
            this.back = this.bottom;
            this.bottom = this.front;
            this.front = temp;
        }

        public void rollWest() {
            rollEast();
            rollEast();
            rollEast();
        }

        public void rollNorth() {
            rollSouth();
            rollSouth();
            rollSouth();
        }
    }
}
