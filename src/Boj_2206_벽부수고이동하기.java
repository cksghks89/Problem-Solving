/*
    Boj 2206. 벽 부수고 이동하기
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2206_벽부수고이동하기 {
    static int[][] map;
    static int N, M;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static class Node {
        int x, y;
        int isBreak;
        int cost;

        public Node(int x, int y, int isBreak, int cost) {
            this.x = x;
            this.y = y;
            this.isBreak = isBreak;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 1));

        boolean[][][] visited = new boolean[2][N][M];

        int result = -1;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                result = cur.cost;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isInRange(nx, ny)) {
                    if (map[nx][ny] == 1 && cur.isBreak == 0 && !visited[1][nx][ny]) {
                        visited[1][nx][ny] = true;
                        queue.offer(new Node(nx, ny, 1, cur.cost + 1));
                    }else if(map[nx][ny] == 0 && !visited[cur.isBreak][nx][ny]){
                        visited[cur.isBreak][nx][ny] = true;
                        queue.offer(new Node(nx, ny, cur.isBreak, cur.cost + 1));
                    }
                }
            }
        }
        return result;
    }

    static boolean isInRange(int y, int x) {
        if (y < 0 || x < 0 || y >= N || x >= M) {
            return false;
        }

        return true;
    }
}
