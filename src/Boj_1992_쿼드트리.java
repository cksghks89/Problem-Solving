/*
    Boj 1992. 쿼드 트리
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1992_쿼드트리 {
    static int[][] map;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        dnc(0, 0, N);
        System.out.println(sb.toString());
    }

    static void dnc(int x, int y, int size) {
        int cnt = 0;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                cnt += map[i][j];
            }
        }

        if (cnt == size * size) {
            sb.append(1);
            return;
        } else if (cnt == 0) {
            sb.append(0);
            return;
        }

        sb.append('(');
        dnc(x, y, size / 2);
        dnc(x, y + size / 2, size / 2);
        dnc(x + size / 2, y, size / 2);
        dnc(x + size / 2, y + size / 2, size / 2);
        sb.append(')');
    }
}
