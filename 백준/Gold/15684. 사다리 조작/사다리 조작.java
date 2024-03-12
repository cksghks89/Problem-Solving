import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, H;
    private static int[][] ladder;
    private static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // (i, j) 는 (i, j)에서 오른쪽으로 연결된 사다리
        ladder = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder[a][b] = 1;
        }

        if (simulation()) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            subSet(1, 1, 0, i);
            if (answer != -1) break;
        }

        System.out.println(answer);
    }

    private static void subSet(int x, int y, int cnt, int last) {
        if (answer != -1) return;
        if (cnt == last) {
            if (simulation()) {
                answer = last;
            }
            return;
        }

        for (int i = y; i < N; i++) {
            if (ladder[x][i - 1] == 1 || ladder[x][i + 1] == 1 || ladder[x][i] == 1) continue;
            ladder[x][i] = 1;
            subSet(x, (i + 1) % N + 1, cnt + 1, last);
            ladder[x][i] = 0;
        }

        for (int i = x + 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (ladder[i][j - 1] == 1 || ladder[i][j + 1] == 1 || ladder[i][j] == 1) continue;
                ladder[i][j] = 1;
                subSet(i, (j + 1) % N + 1, cnt + 1, last);
                ladder[i][j] = 0;
            }
        }
    }

    private static boolean simulation() {
        for (int i = 1; i <= N; i++) {
            int cur = i;

            for (int j = 1; j <= H; j++) {
                if (ladder[j][cur] == 1) {
                    cur += 1;
                } else if (ladder[j][cur - 1] == 1) {
                    cur -= 1;
                }
            }

            if (i != cur) return false;
        }

        return true;
    }
}
