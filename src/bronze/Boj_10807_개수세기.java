/*
    Boj 10807. 개수 세기
    level. bronze 5
    solved by 송찬환
 */
package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_10807_개수세기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int v = Integer.parseInt(br.readLine());

        long cnt = Arrays.stream(nums).filter((x) -> x == v).count();

        System.out.println(cnt);
    }
}
