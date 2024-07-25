import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (n % 2 == 1 && m % 2 == 1) {
            // 특수처리
            int[][] seq = new int[n][m];
            int x = 0;
            int y = 1;
            int id = 1;

            while (true) {
                seq[x++][y] = id++;
                seq[x][y++] = id++;
                seq[x][y] = id++;

                if (y == m - 1) {
                    break;
                } else {
                    x -= 1;
                    seq[x][y++] = id++;
                }
            }

            for (int i = m - 1; i > 0; i--) {
                seq[2][i] = id++;
            }

            insertSeq(seq, 3, id, n, m);
            printAnswer(seq, n * m - 1);
        } else {
            int even = n % 2 == 0 ? n : m;
            int other = even == n ? m : n;

            int[][] seq = new int[even][other];

            insertSeq(seq, 0, 1, even, other);
            if (seq.length != n) {
                seq = rotate(seq);
            }
            printAnswer(seq, n * m);
        }
    }

    private static void printAnswer(int[][] seq, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append(end).append('\n');

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int x = 0;
        int y = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (seq[i][j] == 1) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, 1});

        while (true) {
            int[] cur = queue.poll();
            if (cur[2] == end) {
                sb.append(cur[0] + 1).append(" ").append(cur[1] + 1).append('\n');
                break;
            }

            sb.append(cur[0] + 1).append(" ").append(cur[1] + 1).append('\n');
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (isInRange(nx, ny) && seq[nx][ny] == cur[2] + 1) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }

        System.out.println(sb);
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static int[][] rotate(int[][] mat) {
        int[][] result = new int[mat[0].length][mat.length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = mat[j][result.length - 1 - i];
            }
        }

        return result;
    }

    private static void insertSeq(int[][] seq, int start, int id, int r, int c) {
        int y = 1;
        for (int i = start; i < r; i++) {
            if (y == 1) {
                for (; y < c; y++) {
                    seq[i][y] = id++;
                }
                y--;
            } else {
                for (; y > 0; y--) {
                    seq[i][y] = id++;
                }
                y++;
            }
        }

        for (int i = r - 1; i >= 0; i--) {
            seq[i][0] = id++;
        }
    }
}
