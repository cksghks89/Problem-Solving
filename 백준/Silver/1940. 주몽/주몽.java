import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(nums);

        int p1 = 0;
        int p2 = N - 1;

        int answer = 0;

        while (p1 < p2) {
            int sum = nums[p1] + nums[p2];

            if (sum == M) {
                answer++;
                p1++;
                p2--;
            } else if (sum < M) {
                p1++;
            } else {
                p2--;
            }
        }

        System.out.println(answer);
    }
}