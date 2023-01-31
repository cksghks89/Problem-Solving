/*
    Boj 3273. 두 수의 합
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_3273_두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            int idx = Arrays.binarySearch(arr, x - arr[i]);
            if (idx < 0 || idx == i) {
                continue;
            }
            cnt += 1;
        }
        System.out.println(cnt / 2);
    }
}
