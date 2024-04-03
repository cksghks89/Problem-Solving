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

        int result = lis(arr);
        System.out.println(result);
    }

    private static int lis(int[] arr) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int idx = Collections.binarySearch(list, arr[i]);
            if (idx < 0) idx = -idx - 1;

            if (idx == list.size()) list.add(arr[i]);
            else list.set(idx, arr[i]);
        }

        return list.size();
    }
}
