/*
    Boj 1914. 하노이 탑
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Boj_1914_하노이탑 {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        BigInteger res = new BigInteger("1");

        for(int i = 0; i < N; i++){
            res = res.multiply(new BigInteger("2"));
        }
        res = res.subtract(new BigInteger("1"));
        System.out.println(res);

        if (N <= 20) {
            sb = new StringBuilder();
            recursion(N, 1, 2, 3);
            System.out.print(sb.toString());
        }
    }

    static void recursion(int n, int src, int by, int dest) {
        if (n == 1) {
            sb.append(src).append(" ").append(dest).append("\n");
            return;
        }
        recursion(n - 1, src, dest, by);
        sb.append(src).append(" ").append(dest).append("\n");
        recursion(n - 1, by, src, dest);
    }
}
