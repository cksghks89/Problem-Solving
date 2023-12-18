import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int m = Integer.parseInt(br.readLine());
        int[] B = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] accSumA = new int[n];
        int[] accSumB = new int[m];
        accSumA[0] = A[0];
        accSumB[0] = B[0];
        for (int i = 1; i < n; i++) accSumA[i] = accSumA[i - 1] + A[i];
        for (int i = 1; i < m; i++) accSumB[i] = accSumB[i - 1] + B[i];

        int[] allCaseA = getAllCaseArr(n, accSumA);
        int[] allCaseB = getAllCaseArr(m, accSumB);

        long answer = 0;

        for (int i = 0; i < allCaseA.length; i++) {
            int sameNumCnt = getSameNum(allCaseA, i);
            i += sameNumCnt - 1;
            int find = T - allCaseA[i];

            int idx = Arrays.binarySearch(allCaseB, find);
            if (idx < 0) continue;

            answer += (long) sameNumCnt * getSameNum(allCaseB, idx);
        }

        System.out.println(answer);
    }

    private static int getSameNum(int[] arr, int idx) {
        int cur = arr[idx];
        int cnt = 0;
        int leftIdx = idx;
        while (leftIdx >= 0) {
            if (arr[leftIdx--] != cur) break;
            cnt += 1;
        }
        int rightIdx = idx;
        while (rightIdx + 1 < arr.length) {
            if (arr[++rightIdx] != cur) break;
            cnt += 1;
        }
        return cnt;
    }

    private static int[] getAllCaseArr(int len, int[] acc) {
        int idx = 0;
        int[] arr = new int[len * (len + 1) / 2];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i == 0) {
                    arr[idx++] = acc[j];
                } else {
                    arr[idx++] = acc[j] - acc[i - 1];
                }
            }
        }
        Arrays.sort(arr);
        return arr;
    }
}