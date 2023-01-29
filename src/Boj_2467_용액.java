/*
    Boj 2467. 용액
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2467_용액 {
    static int N;
    static int[] arr;
    static int idx1, idx2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        twoPointer();

        System.out.println(arr[idx1] + " " + arr[idx2]);
    }

    static void twoPointer() {
        int p1 = 0;
        int p2 = N - 1;

        int minSum = Integer.MAX_VALUE;

        while (p1 < p2) {
            int sum = arr[p1] + arr[p2];

            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                idx1 = p1;
                idx2 = p2;
            }

            if (sum == 0) {
                break;
            } else if (sum > 0) {
                p2--;
            } else {
                p1++;
            }
        }
    }
}
