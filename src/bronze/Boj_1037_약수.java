/*
    Boj 1037. 약수
    level. bronze 1
    solved by 송찬환
 */
package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_1037_약수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        int[] arr = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        System.out.println(arr[0] * arr[arr.length - 1]);
    }
}
