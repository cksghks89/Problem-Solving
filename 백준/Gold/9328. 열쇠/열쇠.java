import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int h, w;
    private static char[][] map;
    private static boolean[][] visited;

    private static List<int[]> boundaryPoint;
    private static List<int[]> documentPoint;
    private static Set<Character> hasKeys;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            // 초기화 -------- start
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            visited = new boolean[h][w];
            boundaryPoint = new ArrayList<>();
            documentPoint = new ArrayList<>();
            hasKeys = new HashSet<>();

            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = input.charAt(j);

                    if (map[i][j] != '*' && (j == 0 || j == w - 1 || i == 0 || i == h - 1)) {
                        boundaryPoint.add(new int[]{i, j});
                    }

                    if (map[i][j] == '$') {
                        documentPoint.add(new int[]{i, j});
                    }
                }
            }

            String initKey = br.readLine();
            for (char key : initKey.toCharArray()) hasKeys.add(key);
            // 초기화 -------- end

            // 키 찾기 -- start
            boolean stop = false;
            while (!stop) {
                stop = true;
                int keySize = hasKeys.size();
                for (int i = 0; i < boundaryPoint.size(); i++) {

                    int[] point = boundaryPoint.get(i);
                    findKeyBfs(point[0], point[1]);

                    if (keySize != hasKeys.size()) {
                        stop = false;
                        break;
                    }
                }
            }
            // 키 찾기 -- end
            int answer = 0;
            for (int[] cur : documentPoint) {
                answer += findDocumentBfs(cur[0], cur[1]);
            }

            System.out.println(answer);
        }
    }

    private static int findDocumentBfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        visited = new boolean[h][w];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if ('A' <= map[cur[0]][cur[1]] && map[cur[0]][cur[1]] <= 'Z'
                    && !hasKeys.contains(Character.toLowerCase(map[cur[0]][cur[1]]))) {
                continue;
            }

            if (checkBoundary(cur[0], cur[1])) {
                return 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != '*') {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return 0;
    }

    private static void findKeyBfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if ('A' <= map[cur[0]][cur[1]] && map[cur[0]][cur[1]] <= 'Z'
                    && !hasKeys.contains(Character.toLowerCase(map[cur[0]][cur[1]]))) {
                continue;
            }

            if (checkKey(cur[0], cur[1])) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != '*') {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean checkKey(int x, int y) {
        if ('a' <= map[x][y] && map[x][y] <= 'z' && !hasKeys.contains(map[x][y])) {
            hasKeys.add(map[x][y]);
            visited = new boolean[h][w];
            return true;
        }
        return false;
    }

    private static boolean checkBoundary(int x, int y) {
        return x == 0 || x == h - 1 || y == 0 || y == w - 1;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }
}