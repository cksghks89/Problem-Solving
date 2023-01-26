/*
    Boj 2230. 수 고르기
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2230_수고르기 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(twoPointer());
    }

    static int twoPointer() {
        Arrays.sort(arr);

        int diff = Integer.MAX_VALUE;

        int p1 = 0;
        int p2 = 0;

        while (p2 < arr.length) {
            int cur = arr[p2] - arr[p1];

            if (cur < M) {
                p2++;
                continue;
            }

            if (cur == M) {
                diff = M;
                break;
            }

            diff = Math.min(diff, cur);
            p1++;
        }
        return diff;
    }
}
