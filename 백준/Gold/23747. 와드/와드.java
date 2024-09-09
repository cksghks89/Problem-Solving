import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C;
    private static char[][] board;
    private static boolean[][] visited;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static int hr, hc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        hr = Integer.parseInt(st.nextToken()) - 1;
        hc = Integer.parseInt(st.nextToken()) - 1;

        String op = br.readLine();
        for (int i = 0; i < op.length(); i++) {
            char cur = op.charAt(i);

            if (cur == 'W') {
                warding(hr, hc, board[hr][hc]);
            } else if (cur == 'U') {
                hr -= 1;
            } else if (cur == 'D') {
                hr += 1;
            } else if (cur == 'L') {
                hc -= 1;
            } else if (cur == 'R') {
                hc += 1;
            }
        }

        char[][] answer = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                answer[i][j] = '#';
                if (visited[i][j]) answer[i][j] = '.';
            }
        }

        answer[hr][hc] = '.';
        for (int i = 0; i < 4; i++) {
            int nx = hr + dx[i];
            int ny = hc + dy[i];
            if (isInRange(nx, ny)) answer[nx][ny] = '.';
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(answer[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void warding(int x, int y, char v) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && board[nx][ny] == v) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
