/*
    Boj 16953. A -> B
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16953_AtoB {
    static int rtn = -1;
    static long B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        recursion(A, 1);

        System.out.println(rtn);
    }

    static void recursion(long num, int cnt) {
        if (num > B) {
            return;
        }
        if (num == B) {
            rtn = cnt;
            return;
        }

        recursion(num * 2, cnt + 1);
        recursion(num * 10 + 1L, cnt + 1);
    }
}
