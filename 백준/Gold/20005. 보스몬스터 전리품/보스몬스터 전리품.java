import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, P;
    private static char[][] field;
    private static int HP;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static int[] dps;

    private static class Node {
        char id;
        int x;
        int y;
        int time;

        public Node(char id, int time) {
            this.id = id;
            this.time = time;
        }

        public Node(char id, int x, int y, int time) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        // 임력 및 초기화 ---- start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        field = new char[M][N];
        dps = new int['z' + 1];

        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                field[i][j] = line.charAt(j);
                if ('a' <= field[i][j] && field[i][j] <= 'z') {
                    queue.offer(new Node(field[i][j], i, j, 0));
                    field[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            dps[st.nextToken().charAt(0)] = Integer.parseInt(st.nextToken());
        }

        HP = Integer.parseInt(br.readLine());
        // 임력 및 초기화 ---- end


        // 로직 ---- start
        List<Node> times = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int time = bfs(cur);
            times.add(new Node(cur.id, time));
        }

        times.sort((o1, o2) -> o1.time - o2.time);


        int playerCount = 0;
        int totalDps = 0;
        int lastTime = 0;
        for (int i = 0; i < times.size(); i++) {
            if (lastTime == times.get(i).time) {
                playerCount += 1;
                totalDps += dps[times.get(i).id];
                continue;
            }

            HP -= (times.get(i).time - lastTime) * totalDps;

            if (HP <= 0) break;

            lastTime = times.get(i).time;
            playerCount += 1;
            totalDps += dps[times.get(i).id];
        }
        // 로직 ---- end


        // 출력부 ---- start
        System.out.println(playerCount);
        // 출력부 ---- ㄷnd
    }

    private static int bfs(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);

        boolean[][] visited = new boolean[M][N];

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;

            if (field[cur.x][cur.y] == 'B') {
                return cur.time;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isInRange(nx, ny) && field[nx][ny] != 'X' && !visited[nx][ny]) {
                    queue.offer(new Node(cur.id, nx, ny, cur.time + 1));
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }
}
