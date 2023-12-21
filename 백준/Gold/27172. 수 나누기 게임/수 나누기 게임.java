import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    private static int N;
    private static int max;
    private static HashSet<Integer> numSet;
    private static int[] winCnt;
    private static int[] loseCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numSet = new HashSet<>();

        N = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < N; i++) {
            numSet.add(input[i]);
            max = Math.max(max, input[i]);
        }

        winCnt = new int[max + 1];
        loseCnt = new int[max + 1];

        for (Integer num : numSet) {
            calculate(num);
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < N; i++) {
            int curResult = winCnt[input[i]] - loseCnt[input[i]];
            sb.append(curResult).append(' ');
        }
        System.out.println(sb);
    }

    // 이긴다는 것은 상대방은 졌다는 것.
    private static void calculate(int num) {
        int mul = 2;
        int cnt = 0;

        while (mul * num <= max) {
            if (numSet.contains(mul * num)) {
                cnt += 1;
                loseCnt[mul * num] += 1;
            }
            mul += 1;
        }
        winCnt[num] = cnt;
    }
}
