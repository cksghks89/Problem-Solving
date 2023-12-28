import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int T;
    private static int[][] status;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        status = new int[T][8];

        for (int i = 0; i < T; i++) {
            status[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[T];
            recur(idx - 1, direction, visited);
        }

        int answer = 0;
        for (int i = 0; i < T; i++) {
            answer += status[i][0];
        }

        System.out.println(answer);
    }

    private static void recur(int idx, int direction, boolean[] visited) {
        visited[idx] = true;

        int left = status[idx][6];
        int right = status[idx][2];

        if (idx != 0 && !visited[idx - 1] && (left != status[idx - 1][2])) {
            recur(idx - 1, -direction, visited);
        }

        if (idx != T - 1 && !visited[idx + 1] && (right != status[idx + 1][6])) {
            recur(idx + 1, -direction, visited);
        }

        rotate(idx, direction);
    }

    private static void rotate(int idx, int direction) {
        if (direction == 1) {
            // 시계방향 0 -> 1, 1 -> 2, 7 -> 0
            int start = status[idx][7];
            for (int i = 7; i > 0; i--) {
                status[idx][i] = status[idx][i - 1];
            }
            status[idx][0] = start;
        } else if (direction == -1) {
            // 반시계 방향 0 -> 7, 1 -> 0, 2 -> 1
            int start = status[idx][0];
            for (int i = 0; i < 8; i++) {
                status[idx][i] = status[idx][(i + 1) % 8];
            }
            status[idx][7] = start;
        }
    }
}
