/*
    Boj 10026. 적록색약
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_10026_적록색약 {
    static char[][] picture;
    static int N;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static int cnt = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        picture = new char[N][N];

        for(int i = 0; i < N; i++){
            String str = br.readLine().trim();
            for(int j = 0; j < N; j++){
                picture[i][j] = str.charAt(j);
            }
        }
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.print(cnt + " ");

        cnt = 0;
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(picture[i][j] == 'G'){
                    picture[i][j] = 'R';
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.print(cnt);
    }
    static void dfs(int x, int y){
        if(visited[x][y]){
            return;
        }
        visited[x][y] = true;

        for(int k = 0; k < 4; k++){
            if(0 <= x + dx[k] && x + dx[k] < N && 0 <= y + dy[k] && y + dy[k] < N){
                if(picture[x+dx[k]][y+dy[k]] == picture[x][y]){
                    dfs(x + dx[k], y + dy[k]);
                }
            }
        }
    }
}
