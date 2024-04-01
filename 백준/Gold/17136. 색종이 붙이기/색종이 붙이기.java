import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static int answer = 30;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[10][10];

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bruteForce(0, 0, new boolean[10][10], new int[6]);

        if (answer == 30) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void bruteForce(int x, int y, boolean[][] visited, int[] cnt) {
        if (x == 10) {
            int sum = Arrays.stream(cnt).sum();
            answer = Math.min(answer, sum);
            return;
        }
        if (y == 10) {
            bruteForce(x + 1, 0, visited, cnt);
            return;
        }
        if (map[x][y] == 0 || visited[x][y]) {
            bruteForce(x, y + 1, visited, cnt);
            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (cnt[i] == 5) continue;

            boolean[][] cover = cover(x, y, i, visited);
            if (cover == null) continue;
            cnt[i] += 1;
            bruteForce(x, y + 1, cover, cnt);
            cnt[i] -= 1;
        }
    }

    private static boolean[][] cover(int x, int y, int size, boolean[][] visited) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!isInRange(x + i, y + j)) return null;
                if (map[x + i][y + j] == 0 || visited[x + i][y + j]) return null;
            }
        }

        boolean[][] copyVisited = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                copyVisited[i][j] = visited[i][j];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copyVisited[x + i][y + j] = true;
            }
        }
        return copyVisited;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < 10 && 0 <= y && y < 10;
    }
}
