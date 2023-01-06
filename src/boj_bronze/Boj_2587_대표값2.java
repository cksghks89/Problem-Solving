/*
    Boj 2587. 대표값2
    level. bronze 2
    solved by 송찬환
 */
package boj_bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_2587_대표값2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = new int[5];
        for(int i = 0; i < 5; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        int avg = (int)Arrays.stream(nums).average().getAsDouble();

        Arrays.sort(nums);
        System.out.println(avg);
        System.out.println(nums[2]);
    }
}
