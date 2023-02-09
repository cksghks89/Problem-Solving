/*
    Boj 2023. 신기한 소수
    level. gold 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2023_신기한소수 {
    static int N;
    static int num = 0;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        perm(0);
        System.out.print(sb.toString());
    }

    static void perm(int cnt) {
        if (cnt == N) {
            sb.append(num).append("\n");
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (cnt == 0 && i == 0) {
                continue;
            }
            num *= 10;
            num += i;
            if (isPrime(num)) {
                perm(cnt + 1);
            }
            num /= 10;
        }
    }

    static boolean isPrime(int num) {
        if (num == 1 || num == 0) {
            return false;
        }

        boolean rtn = true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                rtn = false;
            }
        }
        return rtn;
    }
}
