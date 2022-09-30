/*
    Boj 2210. 숫자판 점프
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Boj_2210_숫자판점프 {
    static HashSet<String> set = new HashSet<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[5][];

        for(int i = 0; i < 5; i++){
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                StringBuilder sb = new StringBuilder();
                dfs(sb, map, i, j, 0);
            }
        }
        System.out.println(set.size());
    }

    public static void dfs(StringBuilder sb, int[][] map, int a, int b, int cnt){
        if(cnt == 6){
            set.add(sb.toString());
            return;
        }

        for(int i = 0; i < 4; i++){
            sb.append(map[a][b]);
            if(checkRange(a+dx[i], b+dy[i])){
                dfs(sb, map, a+dx[i], b+dy[i], cnt+1);
            }
            sb.deleteCharAt(cnt);
        }
    }
    public static boolean checkRange(int x, int y){
        return (0<=x && x<5) && (0 <= y && y < 5);
    }
}
