import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] arr;
    static long minVal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        minVal = Long.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(arr);

        long[] result = new long[3];
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                long cur = arr[i] + arr[j];
                int idx = getMinIdx(i, j);

                long mix = Math.abs(cur + arr[idx]);
                if (mix < minVal) {
                    minVal = mix;
                    result[0] = arr[i];
                    result[1] = arr[j];
                    result[2] = arr[idx];
                    if(mix == 0) break;
                }
            }
        }

        Arrays.sort(result);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    static int getMinIdx(int idx1, int idx2) {
        long cur = arr[idx1] + arr[idx2];

        int idx = Arrays.binarySearch(arr, -cur);

        if (idx < 0) idx = -idx - 1;

        int candidate1 = idx - 1;
        while (isInRange(candidate1) && (candidate1 == idx1 || candidate1 == idx2)) {
            candidate1 -= 1;
        }
        int candidate2 = idx;
        while (isInRange(candidate2) && (candidate2 == idx1 || candidate2 == idx2)) {
            candidate2 += 1;
        }

        if (isInRange(candidate1) && isInRange(candidate2)) {
            if (Math.abs(cur + arr[candidate1]) < Math.abs(cur + arr[candidate2])) {
                return candidate1;
            } else {
                return candidate2;
            }
        } else if (isInRange(candidate1)) {
            return candidate1;
        } else {
            return candidate2;
        }
    }

    static boolean isInRange(int x) {
        return 0 <= x && x < N;
    }
}
