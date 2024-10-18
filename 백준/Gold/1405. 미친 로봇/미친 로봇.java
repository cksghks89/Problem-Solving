import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {
    private static int N;

    private static boolean[][] visited;
    private static double[] d;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static double answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        visited = new boolean[50][50];
        d = new double[4];

        N = Integer.parseInt(st.nextToken());
        d[0] = Integer.parseInt(st.nextToken()) / 100.0;
        d[1] = Integer.parseInt(st.nextToken()) / 100.0;
        d[2] = Integer.parseInt(st.nextToken()) / 100.0;
        d[3] = Integer.parseInt(st.nextToken()) / 100.0;

        visited[20][20] = true;
        subSet(0, 20, 20, 1.0);

        if (answer == 1) {
            System.out.println("1.0");
        } else {
            System.out.println(new BigDecimal(answer));
        }
    }

    private static void subSet(int cnt, int x, int y, double prob) {
        if (cnt == N) {
            answer += prob;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            subSet(cnt + 1, nx, ny, prob * d[i]);
            visited[nx][ny] = false;
        }
    }
}
