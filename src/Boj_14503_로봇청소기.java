/*
    Boj 14503. 로봇 청소기
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_14503_로봇청소기 {
    static int[][] map;
    static boolean[][] visited;
    static int count = 0;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cleanMap(r, c, d);
        System.out.println(count);
    }

    static void cleanMap(int r, int c, int d) {
        if(map[r][c] == 0 && !visited[r][c]){
            visited[r][c] = true;
            count++;
        }

        int left = d;

        for(int i = 0; i < 4; i++){
            left = leftDirection(left);

            int nx = r + dx[left];
            int ny = c + dy[left];

            if(isInRange(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]){
                cleanMap(nx, ny, left);
                return;
            }
        }

        int back = backDirection(d);
        int backX = r + dx[back];
        int backY = c + dy[back];

        if(map[backX][backY] != 1){
            cleanMap(backX, backY, d);
        }else{
            return;
        }
    }

    static boolean isInRange(int x, int y){
        return (0 <= x && x < N) && (0 <= y && y < M);
    }

    static int leftDirection(int d){
        return (d - 1 + 4) % 4;
    }

    static int backDirection(int d){
        int back = leftDirection(d);
        back = leftDirection(back);
        return back;
    }
}
