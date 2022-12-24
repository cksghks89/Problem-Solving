/*
    Boj 2630. 색종이 만들기
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2630_색종이만들기 {
    static boolean[][] map;
    static int N;

    static int zeroCnt = 0;
    static int oneCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }
        divideConquer(0, 0, N);

        System.out.println(zeroCnt);
        System.out.println(oneCnt);
    }

    static void divideConquer(int x, int y, int size) {
        if (size == 1) {
            if (map[x][y]) {
                oneCnt += 1;
            } else {
                zeroCnt += 1;
            }
            return;
        }

        String rtn = checkBox(x, y, size);
        if (rtn.equals("zero")) {
            zeroCnt += 1;
            return;
        } else if (rtn.equals("one")) {
            oneCnt += 1;
            return;
        }

        divideConquer(x, y, size / 2);
        divideConquer(x + size / 2, y, size / 2);
        divideConquer(x, y + size / 2, size / 2);
        divideConquer(x + size / 2, y + size / 2, size / 2);
    }

    static String checkBox(int x, int y, int size) {
        boolean zero = true;
        boolean one = true;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j]) {
                    zero = false;
                }
                if (!map[i][j]) {
                    one = false;
                }
            }
        }

        if (zero) return "zero";
        if (one) return "one";

        return "";
    }
}
