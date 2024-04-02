import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] map;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static int answer = Integer.MAX_VALUE;

    private static class Node {
        int[][] map;
        int x;
        int y;
        int cnt;
        String key;

        public Node(int x, int y, int[][] map, int cnt) {
            this.x = x;
            this.y = y;
            this.map = map;
            this.cnt = cnt;
            this.key = flatten(map);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[3][3];

        int sx = 0;
        int sy = 0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    sx = i;
                    sy = j;
                }
            }
        }

        System.out.println(bfs(sx, sy));
    }

    private static int bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x, y, map, 0));

        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visited.contains(cur.key)) continue;
            visited.add(cur.key);

            if ("123456780".equals(cur.key)) return cur.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isInRange(nx, ny)) {
                    int[][] copyMap = swapAndCopy(cur.map, cur.x, cur.y, nx, ny);
                    queue.offer(new Node(nx ,ny, copyMap, cur.cnt + 1));
                }
            }
        }

        return -1;
    }

    private static int[][] swapAndCopy(int[][] board, int x, int y, int nx, int ny) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = board[i][j];
            }
        }

        int temp = copy[x][y];
        copy[x][y] = copy[nx][ny];
        copy[nx][ny] = temp;

        return copy;
    }

    private static String flatten(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < 3 && 0 <= y && y < 3;
    }
}
