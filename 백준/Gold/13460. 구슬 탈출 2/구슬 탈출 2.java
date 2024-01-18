import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Board {
        char[][] map;
        int count;
        int[] red;
        int[] blue;
        boolean isFail;

        public Board(char[][] map, int count, int[] red, int[] blue, boolean isFail) {
            this.map = map;
            this.count = count;
            this.red = red;
            this.blue = blue;
            this.isFail = isFail;
        }
    }

    private static class Pos {
        int[] red;
        int[] blue;

        public Pos(int[] red, int[] blue) {
            this.red = Arrays.copyOf(red, 2);
            this.blue = Arrays.copyOf(blue, 2);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pos) {
                Pos o = (Pos) obj;
                return this.red[0] == o.red[0] && this.red[1] == o.red[1] &&
                        this.blue[0] == o.blue[0] && this.blue[1] == o.blue[1];
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(red[0]);
            result += Integer.hashCode(red[1]);
            result += Integer.hashCode(blue[0]);
            result += Integer.hashCode(blue[1]);
            return result;
        }
    }

    private static int N, M;
    private static Set<Pos> visited;

    private static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new HashSet<>();
        char[][] initMap = new char[N][M];
        int[] red = null;
        int[] blue = null;

        for (int i = 0; i < N; i++) {
            String lineInput = br.readLine();
            for (int j = 0; j < M; j++) {
                initMap[i][j] = lineInput.charAt(j);
                if (initMap[i][j] == 'R') {
                    red = new int[]{i, j};
                } else if (initMap[i][j] == 'B') {
                    blue = new int[]{i, j};
                }
            }
        }

        Board init = new Board(initMap, 0, red, blue, false);

        getCount(init);

        if (answer > 10) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void getCount(Board board) {
        Queue<Board> queue = new ArrayDeque<>();
        queue.offer(board);

        while (!queue.isEmpty()) {
            Board cur = queue.poll();

            // 카운트가 10번 초과하면 탈출
            if (cur.count > 10) break;

            Pos curPos = new Pos(cur.red, cur.blue);
            if (visited.contains(curPos)) continue;
            visited.add(curPos);

            Board left = left(cur);
            if (answer != -1) return;
            if (!left.isFail) queue.offer(left);

            Board right = right(cur);
            if (answer != -1) return;
            if (!right.isFail) queue.offer(right);

            Board up = up(cur);
            if (answer != -1) return;
            if (!up.isFail) queue.offer(up);

            Board down = down(cur);
            if (answer != -1) return;
            if (!down.isFail) queue.offer(down);
        }
    }

    private static Board left(Board board) {
        char[][] copyMap = copyMap(board.map);
        int[] red = new int[]{board.red[0], board.red[1]};
        int[] blue = new int[]{board.blue[0], board.blue[1]};

        // 더 왼쪽에 있는 공 먼저 이동
        if (red[1] <= blue[1]) {
            copyMap[red[0]][red[1]] = '.';
            while (true) {
                if (copyMap[red[0]][red[1] - 1] == '#') {
                    break;
                } else if (copyMap[red[0]][red[1] - 1] == '.') {
                    red[1] -= 1;
                } else if (copyMap[red[0]][red[1] - 1] == 'O') {
                    red = null;
                    break;
                }
            }
            if (red != null) {
                copyMap[red[0]][red[1]] = 'R';
            }

            copyMap[blue[0]][blue[1]] = '.';
            while (true) {
                if (copyMap[blue[0]][blue[1] - 1] == '#' || copyMap[blue[0]][blue[1] - 1] == 'R') {
                    break;
                } else if (copyMap[blue[0]][blue[1] - 1] == '.') {
                    blue[1] -= 1;
                } else if (copyMap[blue[0]][blue[1] - 1] == 'O') {
                    blue = null;
                    break;
                }
            }
            if (blue != null) {
                copyMap[blue[0]][blue[1]] = 'B';
            }

            if (red == null && blue != null) {
                answer = board.count + 1;
                return null;
            } else if (blue == null) {
                return new Board(null, 0, null, null, true);
            } else {
                return new Board(copyMap, board.count + 1, red, blue, false);
            }
        } else {
            copyMap[blue[0]][blue[1]] = '.';
            while (true) {
                if (copyMap[blue[0]][blue[1] - 1] == '#') {
                    break;
                } else if (copyMap[blue[0]][blue[1] - 1] == '.') {
                    blue[1] -= 1;
                } else if (copyMap[blue[0]][blue[1] - 1] == 'O') {
                    blue = null;
                    break;
                }
            }
            if (blue != null) {
                copyMap[blue[0]][blue[1]] = 'B';
            }

            copyMap[red[0]][red[1]] = '.';
            while (true) {
                if (copyMap[red[0]][red[1] - 1] == '#' || copyMap[red[0]][red[1] - 1] == 'B') {
                    break;
                } else if (copyMap[red[0]][red[1] - 1] == '.') {
                    red[1] -= 1;
                } else if (copyMap[red[0]][red[1] - 1] == 'O') {
                    red = null;
                    break;
                }
            }
            if (red != null) {
                copyMap[red[0]][red[1]] = 'R';
            }

            if (red == null && blue != null) {
                answer = board.count + 1;
                return null;
            } else if (blue == null) {
                return new Board(null, 0, null, null, true);
            } else {
                return new Board(copyMap, board.count + 1, red, blue, false);
            }
        }

    }

    private static Board right(Board board) {
        char[][] copyMap = copyMap(board.map);
        int[] red = new int[]{board.red[0], board.red[1]};
        int[] blue = new int[]{board.blue[0], board.blue[1]};

        if (red[1] >= blue[1]) {
            copyMap[red[0]][red[1]] = '.';
            while (true) {
                if (copyMap[red[0]][red[1] + 1] == '#') {
                    break;
                } else if (copyMap[red[0]][red[1] + 1] == '.') {
                    red[1] += 1;
                } else if (copyMap[red[0]][red[1] + 1] == 'O') {
                    red = null;
                    break;
                }
            }
            if (red != null) {
                copyMap[red[0]][red[1]] = 'R';
            }

            copyMap[blue[0]][blue[1]] = '.';
            while (true) {
                if (copyMap[blue[0]][blue[1] + 1] == '#' || copyMap[blue[0]][blue[1] + 1] == 'R') {
                    break;
                } else if (copyMap[blue[0]][blue[1] + 1] == '.') {
                    blue[1] += 1;
                } else if (copyMap[blue[0]][blue[1] + 1] == 'O') {
                    blue = null;
                    break;
                }
            }
            if (blue != null) {
                copyMap[blue[0]][blue[1]] = 'B';
            }

            if (red == null && blue != null) {
                answer = board.count + 1;
                return null;
            } else if (blue == null) {
                return new Board(null, 0, null, null, true);
            } else {
                return new Board(copyMap, board.count + 1, red, blue, false);
            }
        } else {
            copyMap[blue[0]][blue[1]] = '.';
            while (true) {
                if (copyMap[blue[0]][blue[1] + 1] == '#') {
                    break;
                } else if (copyMap[blue[0]][blue[1] + 1] == '.') {
                    blue[1] += 1;
                } else if (copyMap[blue[0]][blue[1] + 1] == 'O') {
                    blue = null;
                    break;
                }
            }
            if (blue != null) {
                copyMap[blue[0]][blue[1]] = 'B';
            }

            copyMap[red[0]][red[1]] = '.';
            while (true) {
                if (copyMap[red[0]][red[1] + 1] == '#' || copyMap[red[0]][red[1] + 1] == 'B') {
                    break;
                } else if (copyMap[red[0]][red[1] + 1] == '.') {
                    red[1] += 1;
                } else if (copyMap[red[0]][red[1] + 1] == 'O') {
                    red = null;
                    break;
                }
            }
            if (red != null) {
                copyMap[red[0]][red[1]] = 'R';
            }

            if (red == null && blue != null) {
                answer = board.count + 1;
                return null;
            } else if (blue == null) {
                return new Board(null, 0, null, null, true);
            } else {
                return new Board(copyMap, board.count + 1, red, blue, false);
            }
        }

    }

    private static Board up(Board board) {
        char[][] copyMap = copyMap(board.map);
        int[] red = new int[]{board.red[0], board.red[1]};
        int[] blue = new int[]{board.blue[0], board.blue[1]};

        if (red[0] <= blue[0]) {
            copyMap[red[0]][red[1]] = '.';
            while (true) {
                if (copyMap[red[0] - 1][red[1]] == '#') {
                    break;
                } else if (copyMap[red[0] - 1][red[1]] == '.') {
                    red[0] -= 1;
                } else if (copyMap[red[0] - 1][red[1]] == 'O') {
                    red = null;
                    break;
                }
            }
            if (red != null) {
                copyMap[red[0]][red[1]] = 'R';
            }

            copyMap[blue[0]][blue[1]] = '.';
            while (true) {
                if (copyMap[blue[0] - 1][blue[1]] == '#' || copyMap[blue[0] - 1][blue[1]] == 'R') {
                    break;
                } else if (copyMap[blue[0] - 1][blue[1]] == '.') {
                    blue[0] -= 1;
                } else if (copyMap[blue[0] - 1][blue[1]] == 'O') {
                    blue = null;
                    break;
                }
            }
            if (blue != null) {
                copyMap[blue[0]][blue[1]] = 'B';
            }

            if (red == null && blue != null) {
                answer = board.count + 1;
                return null;
            } else if (blue == null) {
                return new Board(null, 0, null, null, true);
            } else {
                return new Board(copyMap, board.count + 1, red, blue, false);
            }
        } else {
            copyMap[blue[0]][blue[1]] = '.';
            while (true) {
                if (copyMap[blue[0] - 1][blue[1]] == '#') {
                    break;
                } else if (copyMap[blue[0] - 1][blue[1]] == '.') {
                    blue[0] -= 1;
                } else if (copyMap[blue[0] - 1][blue[1]] == 'O') {
                    blue = null;
                    break;
                }
            }
            if (blue != null) {
                copyMap[blue[0]][blue[1]] = 'B';
            }

            copyMap[red[0]][red[1]] = '.';
            while (true) {
                if (copyMap[red[0] - 1][red[1]] == '#' || copyMap[red[0] - 1][red[1]] == 'B') {
                    break;
                } else if (copyMap[red[0] - 1][red[1]] == '.') {
                    red[0] -= 1;
                } else if (copyMap[red[0] - 1][red[1]] == 'O') {
                    red = null;
                    break;
                }
            }
            if (red != null) {
                copyMap[red[0]][red[1]] = 'R';
            }

            if (red == null && blue != null) {
                answer = board.count + 1;
                return null;
            } else if (blue == null) {
                return new Board(null, 0, null, null, true);
            } else {
                return new Board(copyMap, board.count + 1, red, blue, false);
            }
        }

    }

    private static Board down(Board board) {
        char[][] copyMap = copyMap(board.map);
        int[] red = new int[]{board.red[0], board.red[1]};
        int[] blue = new int[]{board.blue[0], board.blue[1]};

        if (red[0] >= blue[0]) {
            copyMap[red[0]][red[1]] = '.';
            while (true) {
                if (copyMap[red[0] + 1][red[1]] == '#') {
                    break;
                } else if (copyMap[red[0] + 1][red[1]] == '.') {
                    red[0] += 1;
                } else if (copyMap[red[0] + 1][red[1]] == 'O') {
                    red = null;
                    break;
                }
            }
            if (red != null) {
                copyMap[red[0]][red[1]] = 'R';
            }

            copyMap[blue[0]][blue[1]] = '.';
            while (true) {
                if (copyMap[blue[0] + 1][blue[1]] == '#' || copyMap[blue[0] + 1][blue[1]] == 'R') {
                    break;
                } else if (copyMap[blue[0] + 1][blue[1]] == '.') {
                    blue[0] += 1;
                } else if (copyMap[blue[0] + 1][blue[1]] == 'O') {
                    blue = null;
                    break;
                }
            }
            if (blue != null) {
                copyMap[blue[0]][blue[1]] = 'B';
            }

            if (red == null && blue != null) {
                answer = board.count + 1;
                return null;
            } else if (blue == null) {
                return new Board(null, 0, null, null, true);
            } else {
                return new Board(copyMap, board.count + 1, red, blue, false);
            }
        } else {
            copyMap[blue[0]][blue[1]] = '.';
            while (true) {
                if (copyMap[blue[0] + 1][blue[1]] == '#') {
                    break;
                } else if (copyMap[blue[0] + 1][blue[1]] == '.') {
                    blue[0] += 1;
                } else if (copyMap[blue[0] + 1][blue[1]] == 'O') {
                    blue = null;
                    break;
                }
            }
            if (blue != null) {
                copyMap[blue[0]][blue[1]] = 'B';
            }

            copyMap[red[0]][red[1]] = '.';
            while (true) {
                if (copyMap[red[0] + 1][red[1]] == '#' || copyMap[red[0] + 1][red[1]] == 'B') {
                    break;
                } else if (copyMap[red[0] + 1][red[1]] == '.') {
                    red[0] += 1;
                } else if (copyMap[red[0] + 1][red[1]] == 'O') {
                    red = null;
                    break;
                }
            }
            if (red != null) {
                copyMap[red[0]][red[1]] = 'R';
            }

            if (red == null && blue != null) {
                answer = board.count + 1;
                return null;
            } else if (blue == null) {
                return new Board(null, 0, null, null, true);
            } else {
                return new Board(copyMap, board.count + 1, red, blue, false);
            }
        }

    }

    private static char[][] copyMap(char[][] board) {
        char[][] copy = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }
}
