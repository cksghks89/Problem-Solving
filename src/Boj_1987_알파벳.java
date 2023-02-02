/*
    Boj 1987. 알파벳
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj_1987_알파벳 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static Set<Character> set;
    static int maxCnt;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        set = new HashSet<>();
        maxCnt = Integer.MIN_VALUE;

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        set.add(map[0][0]);
        visited[0][0] = true;
        dfs(0, 0, 1);

        System.out.println(maxCnt);
    }

    static void dfs(int x, int y, int cnt) {
        boolean isEnd = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isInRange(nx, ny) && !visited[nx][ny] && !set.contains(map[nx][ny])) {
                isEnd = false;
                visited[nx][ny] = true;
                set.add(map[nx][ny]);
                dfs(nx, ny, cnt + 1);
                set.remove(map[nx][ny]);
                visited[nx][ny] = false;
            }
        }

        if (isEnd) {
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < R) && (0 <= y && y < C);
    }
}
