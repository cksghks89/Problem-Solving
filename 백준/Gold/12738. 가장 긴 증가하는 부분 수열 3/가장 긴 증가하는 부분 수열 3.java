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

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int idx = Collections.binarySearch(answer, arr[i]);

            if (idx < 0) {
                idx = -idx - 1;
                if (idx == answer.size()) answer.add(arr[i]);
                else answer.set(idx, arr[i]);
            } else {
                answer.set(idx, arr[i]);
            }
        }

        System.out.println(answer.size());
    }
}
