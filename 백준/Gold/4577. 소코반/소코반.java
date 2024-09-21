import java.io.*;
import java.util.*;

public class Main {
    private static int R, C;
    private static char[][] board;
    private static List<int[]> dest;
    private static int[] w;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();
        int id = 0;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (R == 0 && C == 0) break;
            id += 1;

            board = new char[R][C];
            dest = new ArrayList<>();
            w = new int[2];

            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    board[i][j] = line.charAt(j);

                    if (board[i][j] == 'w') {
                        w[0] = i;
                        w[1] = j;
                        board[i][j] = '.';
                    } else if (board[i][j] == 'W') {
                        w[0] = i;
                        w[1] = j;
                        board[i][j] = '.';
                        dest.add(new int[]{i, j});
                    } else if (board[i][j] == '+') {
                        dest.add(new int[]{i, j});
                        board[i][j] = '.';
                    } else if (board[i][j] == 'B') {
                        dest.add(new int[]{i, j});
                        board[i][j] = 'b';
                    }
                }
            }

            String command = br.readLine();

            for (int i = 0; i < command.length(); i++) {
                char cur = command.charAt(i);

                if (cur == 'U') {
                    move(0);
                } else if (cur == 'R') {
                    move(1);
                } else if (cur == 'D') {
                    move(2);
                } else if (cur == 'L') {
                    move(3);
                }
                if (isComplete()) break;
            }

            board[w[0]][w[1]] = 'w';
            int count = 0;
            for (int i = 0; i < dest.size(); i++) {
                int[] cur = dest.get(i);

                if (board[cur[0]][cur[1]] == 'b') {
                    board[cur[0]][cur[1]] = 'B';
                    count += 1;
                } else if (board[cur[0]][cur[1]] == 'w') {
                    board[cur[0]][cur[1]] = 'W';
                } else {
                    board[cur[0]][cur[1]] = '+';
                }
            }

            if (count == dest.size()) {
                answer.append("Game " + id + ": complete").append('\n');
            } else {
                answer.append("Game " + id + ": incomplete").append('\n');
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    answer.append(board[i][j]);
                }
                answer.append('\n');
            }
        }

        System.out.println(answer);
    }

    private static void move(int command) {
        int nx = w[0] + dx[command];
        int ny = w[1] + dy[command];

        if (!isMoved(nx, ny)) return;

        if (board[nx][ny] == '.') {
            w[0] = nx;
            w[1] = ny;
        } else if (board[nx][ny] == 'b') {
            int nnx = nx + dx[command];
            int nny = ny + dy[command];

            if (!isMoved(nnx, nny) || board[nnx][nny] != '.') return;

            board[nnx][nny] = 'b';
            board[nx][ny] = '.';
            w[0] = nx;
            w[1] = ny;
        }
    }

    private static boolean isComplete() {
        int count = 0;
        for (int i = 0; i < dest.size(); i++) {
            int[] cur = dest.get(i);

            if (board[cur[0]][cur[1]] == 'b') {
                count += 1;
            }
        }
        return count == dest.size();
    }

    private static boolean isMoved(int x, int y) {
        return (0 <= x && x < R && 0 <= y && y < C) && (board[x][y] != '#');
    }
}
