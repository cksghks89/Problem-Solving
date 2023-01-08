/*
    Boj 2448. 별찍기 - 11
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2448_별찍기11 {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][2 * N];

        divideAndConquer(N, 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N; j++) {
                if(map[i][j] == '*'){
                    sb.append("*");
                }else{
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void divideAndConquer(int cnt, int x, int y) {
        if (cnt == 3) {
            printStar(x, y);
            return;
        }

        divideAndConquer(cnt / 2, x, y + cnt / 2);
        divideAndConquer(cnt / 2, x + cnt / 2, y);
        divideAndConquer(cnt / 2, x + cnt / 2, y + cnt);
    }

    static void printStar(int x, int y) {
        map[x][y + 2] = '*';
        map[x + 1][y + 1] = '*';
        map[x + 1][y + 3] = '*';
        for (int i = 0; i < 5; i++) {
            map[x + 2][y + i] = '*';
        }
    }
}
