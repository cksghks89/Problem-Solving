/*
    Boj 2665. 미로 만들기
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Boj_2665_미로만들기 {
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j) == '0' ? 1 : 0;
            }
        }

        System.out.println(dijkstra());
    }

    static int dijkstra() {
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (dist[nx][ny] > cur.weight + map[nx][ny]) {
                    dist[nx][ny] = cur.weight + map[nx][ny];
                    pq.offer(new Node(nx, ny, dist[nx][ny]));
                }
            }
        }
        return dist[n - 1][n - 1];
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
