/*
    Boj 14891_톱니바퀴
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_14891_톱니바퀴 {
    static boolean[][] wheel;
    static int[][] op;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        wheel = new boolean[4][8];

        for (int i = 0; i < 4; i++) {
            String[] parse = br.readLine().split("");

            for (int j = 0; j < 8; j++) {
                wheel[i][j] = parse[j].equals("1");
            }
        }

        int K = Integer.parseInt(br.readLine());
        op = new int[K][2];

        for (int i = 0; i < K; i++) {
            visited = new boolean[4];
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            dfs(n, d);
        }

        System.out.println(getScore());
    }

    private static int getScore() {
        int score = 0;

        for (int i = 0; i < 4; i++) {
            if (wheel[i][0]) {
                score += Math.pow(2, i);
            }
        }
        return score;
    }

    static void dfs(int n, int d) {
        if (visited[n]) {
            return;
        }
        visited[n] = true;

        boolean right = wheel[n][2];
        boolean left = wheel[n][6];

        rotateWheel(n, d);

        if (isInRange(n + 1) && wheel[n + 1][6] != right) {
            dfs(n + 1, -d);
        }
        if (isInRange(n - 1) && wheel[n - 1][2] != left) {
            dfs(n - 1, -d);
        }
    }

    static boolean isInRange(int x) {
        return 0 <= x && x < 4;
    }

    static void rotateWheel(int n, int d) {
        if (d == 1) {
            boolean temp = wheel[n][7];
            for (int i = 7; i > 0; i--) {
                wheel[n][i] = wheel[n][(i - 1 + 8) % 8];
            }
            wheel[n][0] = temp;

        } else if (d == -1) {
            boolean temp = wheel[n][0];
            for (int i = 0; i < 7; i++) {
                wheel[n][i] = wheel[n][(i + 1) % 8];
            }
            wheel[n][7] = temp;
        }
    }
}
