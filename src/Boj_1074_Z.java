/*
    Boj 1074. Z
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1074_Z {
    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.println(dnc((int)Math.pow(2, N), 0, 0, 0));
    }

    static int dnc(int n, int x, int y, int cnt) {
        if (n == 1) {
            return cnt;
        }

        int sub = n / 2;
        if (r < x + sub && c < y + sub) {
            return dnc(sub, x, y, cnt);
        } else if (r < x + sub && c >= y + sub) {
            return dnc(sub, x, y + sub, cnt + sub * sub);
        } else if (r >= x + sub && c < y + sub) {
            return dnc(sub, x + sub, y, cnt + 2 * sub * sub);
        } else {
            return dnc(sub, x + sub, y + sub, cnt + 3 * sub * sub);
        }
    }
}
