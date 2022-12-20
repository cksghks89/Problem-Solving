/*
    Boj 1965. 상자 넣기
    level. silver 2
    solved by 송찬환
 */
package boj_dp_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_1965_상자넣기 {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] boxes = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        dp = new int[n];

        for (int i = 1; i < n; i++) {
            int max = -1;
            for (int j = 0; j < i; j++) {
                if(boxes[i] > boxes[j]){
                    max = Math.max(dp[j], max);
                }
            }

            dp[i] = max + 1;
        }

        int result = Arrays.stream(dp).max().getAsInt();
        System.out.println(result + 1);
    }
}
