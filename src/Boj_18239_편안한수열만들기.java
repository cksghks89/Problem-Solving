/*
    Boj 18239. 편안한 수열 만들기
    level. gold 3
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_18239_편안한수열만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N >= 4) {
            System.out.println("YES");
            if (K == 1) {
                System.out.println("swap 1 2");
                System.out.println("reverse 2 " + N);
                System.out.println("reverse 2 " + (N - 1));
                System.out.println("reverse 1 " + N);
                System.out.println("reverse 1 " + N);
                return;
            }
            if (K == N - 1) {
                System.out.printf("swap %d %d\n", N - 1, N);
                System.out.printf("reverse %d %d\n", 1, N - 1);
                System.out.printf("reverse %d %d\n", 2, N - 1);
                System.out.printf("reverse %d %d\n", 1, N);
                System.out.printf("reverse %d %d\n", 1, N);
                return;
            }

            System.out.printf("reverse %d %d\n", 1, K);
            System.out.printf("reverse %d %d\n", K + 1, N);
            System.out.printf("reverse %d %d\n", 1, N);
            System.out.printf("reverse %d %d\n", 1, N);
            System.out.printf("reverse %d %d\n", 1, N);
        }

        if (N == 3) {
            System.out.println("NO");
        }

        if (N == 2) {
            System.out.println("YES");
            System.out.println("reverse 1 2");
            System.out.println("reverse 1 2");
            System.out.println("reverse 1 2");
            System.out.println("reverse 1 2");
            System.out.println("reverse 1 2");
            return;
        }
    }
}
