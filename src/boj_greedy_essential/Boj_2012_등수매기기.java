/*
    Boj 2012. 등수 매기기
    level. silver 3
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_2012_등수매기기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] expect = new int[N+1];

        for (int i = 1; i <= N; i++) {
            expect[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(expect);

        long rtn = 0;
        for (int i = 1; i <= N; i++) {
            rtn += Math.abs(expect[i] - i);
        }
        System.out.println(rtn);
    }
}
