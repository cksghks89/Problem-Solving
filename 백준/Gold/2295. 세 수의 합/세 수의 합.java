import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static long[] numArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        numArr = new long[N];

        for (int i = 0; i < N; i++) {
            numArr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(numArr);

        long answer = 0;
        int maxIdx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                long curSum = numArr[i] + numArr[j];

                for (int k = maxIdx; k < N; k++) {
                    long find = numArr[k] - curSum;
                    if (find < 0) continue;
                    int findIdx = Arrays.binarySearch(numArr, find);
                    if (findIdx >= 0) {
                        maxIdx = k;
                        answer = Math.max(answer, curSum + find);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
