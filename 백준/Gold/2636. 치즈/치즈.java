import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int M;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int hours = 0;
        int count = 0;
        while(true){
            int cnt = bfs();
            if(cnt != 0){
                hours += 1;
                count = cnt;
            }else{
                break;
            }
        }
        System.out.println(hours);
        System.out.println(count);
    }

    static int bfs() {
        int cnt = 0;
        visited = new boolean[N][M];
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(0, 0));

        while(!queue.isEmpty()){
            Position cur = queue.poll();

            if(visited[cur.x][cur.y]){
                continue;
            }
            visited[cur.x][cur.y] = true;
            if(map[cur.x][cur.y] == 1){
                map[cur.x][cur.y] = 2;
                cnt++;
                continue;
            }

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isInRange(nx, ny) && !visited[nx][ny]){
                    queue.add(new Position(nx, ny));
                }
            }
        }

        return cnt;
    }

    static boolean isInRange(int x, int y){
        return (0 <= x && x < N) && (0 <= y && y < M);
    }
}
