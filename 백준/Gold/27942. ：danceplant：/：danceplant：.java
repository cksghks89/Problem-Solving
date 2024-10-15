import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] board;

    private static List<int[]>[] list;
    private static int[] dx = {-1, 1, 0, 0};    // 상하좌우
    private static int[] dy = {0, 0, -1, 1};

    private static char[] direction = {'U', 'D', 'L', 'R'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        list = new List[4];

        for (int i = 0; i < 4; i++) {
            list[i] = new ArrayList<>();
        }

        // 상
        list[0].add(new int[]{N / 2 - 1, N / 2 - 1});
        list[0].add(new int[]{N / 2 - 1, N / 2});

        // 하
        list[1].add(new int[]{N / 2, N / 2 - 1});
        list[1].add(new int[]{N / 2, N / 2});

        // 좌
        list[2].add(new int[]{N / 2 - 1, N / 2 - 1});
        list[2].add(new int[]{N / 2, N / 2 - 1});

        // 우
        list[3].add(new int[]{N / 2 - 1, N / 2});
        list[3].add(new int[]{N / 2, N / 2});


        int total = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int score = 0;
            int d = -1;

            for (int i = 0; i < 4; i++) {
                int cur = getPoint(i);
                if (score < cur) {
                    score = cur;
                    d = i;
                }
            }

            if (score <= 0) break;
            total += score;
            sb.append(direction[d]);

            addPosition(d);
        }

        System.out.println(total);
        System.out.println(sb);
    }

    private static void addPosition(int d) {
        List<int[]> newPosition = new ArrayList<>();

        int base = 0;
        int min = 30000;
        int max = -1;
        for (int[] point : list[d]) {
            int x = point[0] + dx[d];
            int y = point[1] + dy[d];

            newPosition.add(new int[]{x, y});

            if (d == 0 || d == 1) {
                min = Math.min(min, y);
                max = Math.max(max, y);
                base = x;
            } else if (d == 2 || d == 3) {
                min = Math.min(min, x);
                max = Math.max(max, x);
                base = y;
            }
        }

        list[d] = newPosition;
        if (d == 0 || d == 1) {
            list[2].add(new int[]{base, min});
            list[3].add(new int[]{base, max});
        } else if (d == 2 || d == 3) {
            list[0].add(new int[]{min, base});
            list[1].add(new int[]{max, base});
        }
    }

    private static int getPoint(int d) {
        int score = 0;

        for (int[] point : list[d]) {
            int x = point[0] + dx[d];
            int y = point[1] + dy[d];

            if (isInRange(x, y)) {
                score += board[x][y];
            }
        }

        return score;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
