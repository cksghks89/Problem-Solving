import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[] answer;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        answer = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] omr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        result = getCorrect(omr);

        subSet(0, omr);

        System.out.println(result);
    }

    private static void subSet(int cnt, int[] omr) {
        if (cnt == K) {
            return;
        }

        // 1. 당기기
        for (int i = 0; i < N; i++) {
            int[] copy = Arrays.copyOf(omr, N);
            for (int j = i; j < N - 1; j++) {
                copy[j] = copy[j + 1];
            }
            copy[N - 1] = -1;

            int count = getCorrect(copy);
            result = Math.max(result, count);

            subSet(cnt + 1, copy);
        }

        // 2. 밀기
        for (int i = 0; i < N; i++) {
            int[] copy = Arrays.copyOf(omr, N);
            for (int j = N - 1; j > i; j--) {
                copy[j] = copy[j - 1];
            }
            copy[i] = -1;

            int count = getCorrect(copy);
            result = Math.max(result, count);

            subSet(cnt + 1, copy);
        }
    }

    private static int getCorrect(int[] omr) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (answer[i] == omr[i]) count += 1;
        }
        return count;
    }
}
