import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static char[][] fields;

    private static boolean[][] visited;
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fields = new char[12][6];

        for (int i = 0; i < 12; i++) {
            fields[i] = br.readLine().toCharArray();
        }

        // R, G, B, P, Y

        // 1. bfs로 같은 색이 4개 이상 붙어있는 블록 찾기
        // 2. 블록 터트리기
        // 3. 아래로 내리기
        // 4. 1번 반복하여 카운트 세기
        int answer = 0;
        while (true) {
            visited = new boolean[12][6];
            boolean result = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (fields[i][j] == '.' || visited[i][j]) continue;
                    if (bfs(i, j)) {
                        result = true;
                    }
                }
            }

            if (!result) break;
            answer += 1;
            blockDown();
        }

        System.out.println(answer);
    }

    private static void blockDown() {
        Queue<Character>[] qArr = new Queue[6];
        for (int i = 0; i < 6; i++) {
            qArr[i] = new ArrayDeque<>();

            for (int j = 11; j >= 0; j--) {
                if (fields[j][i] == '.') continue;
                qArr[i].offer(fields[j][i]);
            }
        }

        for (int i = 0; i < 12; i++) {
            Arrays.fill(fields[i], '.');
        }

        for (int i = 0; i < 6; i++) {
            int idx = 11;
            while (!qArr[i].isEmpty()) {
                char cur = qArr[i].poll();
                fields[idx--][i] = cur;
            }
        }
    }

    private static boolean bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        int cnt = 0;
        char block = fields[x][y];
        List<int[]> visitedPoint = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            visitedPoint.add(new int[]{cur[0], cur[1]});
            cnt += 1;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny] && fields[nx][ny] == block) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        if (cnt >= 4) {
            for (int[] point : visitedPoint) {
                fields[point[0]][point[1]] = '.';
            }
            return true;
        }
        return false;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < 12 && 0 <= y && y < 6;
    }
}
