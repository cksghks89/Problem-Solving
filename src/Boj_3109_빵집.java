/*
    Boj 3109. 빵집
    level. gold 2
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_3109_빵집 {
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static int total;
    static boolean allReturn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = input[j];
            }
        }

        for (int i = 0; i < R; i++) {
            allReturn = false;
            dfs(i, 0);
        }

        System.out.println(total);
    }

    static void dfs(int x, int y) {
        if (y == C - 1) {
            total++;
            allReturn = true;
            return;
        }

        for (int i = 0; i < 3; i++) {
            if(allReturn){
                return;
            }
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isInRange(nx, ny) && map[nx][ny] == '.') {
                map[nx][ny] = 'x';
                dfs(nx, ny);
            }
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < R) && (0 <= y && y < C);
    }
}
