import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> lis = new ArrayList<>();
        int[] idxArr = new int[N];

        for (int i = 0; i < N; i++) {
            int cur = Collections.binarySearch(lis, arr[i]);
            if (cur >= 0) {
                lis.set(cur, arr[i]);
                idxArr[i] = cur;
            } else {
                cur = -cur - 1;
                if (lis.size() == cur) {
                    lis.add(arr[i]);
                } else {
                    lis.set(cur, arr[i]);
                }
                idxArr[i] = cur;
            }
        }

        int len = lis.size();
        int[] result = new int[len];
        int idx = len - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (idx < 0) break;
            if (idx == idxArr[i]) {
                result[idx--] = arr[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(len).append('\n');
        for (int n : result) {
            sb.append(n).append(' ');
        }
        System.out.println(sb.toString());
    }
}
