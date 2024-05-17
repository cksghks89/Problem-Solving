import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dice = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 0 - 5, 1 - 4, 2 - 3

        // 3개짜리 - 4개
        // 2개짜리 - (N-1)*4 + (N-2) * 4
        // 1개짜리 - (N-2)*(N-1)*4 + (N-2)*(N-2)

        if (N == 1) {
            int result = Arrays.stream(dice).sum() - Arrays.stream(dice).max().getAsInt();
            System.out.println(result);
            return;
        }

        int temp1 = Math.min(dice[0], dice[5]);
        int temp2 = Math.min(dice[1], dice[4]);
        int temp3 = Math.min(dice[2], dice[3]);

        long three = temp1 + temp2 + temp3;
        long two = three - Math.max(temp1, Math.max(temp2, temp3));
        long one = Math.min(temp1, Math.min(temp2, temp3));

        long result = three * 4 + ((N - 1L) * 4 + (N - 2L) * 4) * two + ((N - 2L) * (N - 2) + (N - 2L) * (N - 1) * 4) * one;
        System.out.println(result);
    }
}
