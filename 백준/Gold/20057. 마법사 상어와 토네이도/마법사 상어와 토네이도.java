import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] fields;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    private static double[][][] ratio;

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        fields = new int[N][N];
        ratio = new double[4][5][5];

        ratio[0] = new double[][]{
                {0, 0, 0.02, 0, 0},
                {0, 0.1, 0.07, 0.01, 0},
                {0.05, 0, 0, 0, 0},
                {0, 0.1, 0.07, 0.01, 0},
                {0, 0, 0.02, 0, 0}
        };
        ratio[1] = rotate(ratio[0]);
        ratio[2] = rotate(ratio[1]);
        ratio[3] = rotate(ratio[2]);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moveTornado(N / 2, N / 2, 1, 0, 1);

        System.out.println(answer);
    }

    private static void moveTornado(int x, int y, int count, int d, int totalCount) {
        if (x == 0 && y == 0) {
            return;
        }

        if (count == 0) {
            if (d == 1 || d == 3) {
                totalCount += 1;
            }
            d = (d + 1) % 4;
            count = totalCount;
        }

        // 비율 계산
        int nx = x + dx[d];
        int ny = y + dy[d];

        spread(nx, ny, d, ratio[d]);
        moveTornado(nx, ny, count - 1, d, totalCount);
    }

    private static void spread(int x, int y, int d, double[][] mask) {
        int left = x - 2;
        int right = y - 2;

        int alpha = fields[x][y];
        int erase = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int cur = (int) (mask[i][j] * fields[x][y]);
                alpha -= cur;
                if (isInRange(left + i, right + j)) {
                    fields[left + i][right + j] += cur;
                } else {
                    erase += cur;
                }
            }
        }

        int nx = x + dx[d];
        int ny = y + dy[d];
        if (isInRange(nx, ny)) {
            fields[nx][ny] += alpha;
        } else {
            erase += alpha;
        }

        answer += erase;
    }

    private static double[][] rotate(double[][] ratio) {
        double[][] cur = new double[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cur[4 - j][i] = ratio[i][j];
            }
        }
        return cur;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

}
