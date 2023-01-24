/*
    Boj 10610. 30
    level. silver 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_10610_30 {
    static String N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();

        int sum = 0;
        for (int i = 0; i < N.length(); i++) {
            sum += N.charAt(i) - '0';
        }

        if (!N.contains("0") || sum % 3 != 0) {
            System.out.println("-1");
            return;
        }

        char[] bigNumArr = N.toCharArray();

        Arrays.sort(bigNumArr);
        StringBuilder sb = new StringBuilder();
        for (int i = bigNumArr.length - 1; i >= 0; i--) {
            sb.append(bigNumArr[i]);
        }

        System.out.println(sb.toString());
    }
}
