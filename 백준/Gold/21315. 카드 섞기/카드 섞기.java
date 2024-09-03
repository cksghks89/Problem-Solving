import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] input = new int[N];
        for (int i = 0; i < N; i++) input[i] = i + 1;

        for (int i = 1; i < N && Math.pow(2, i) < N; i++) {
            for (int j = 1; j < N && Math.pow(2, j) < N; j++) {
                int[] first = shuffle(input, i);
                int[] second = shuffle(first, j);

                if (isSame(second)) {
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }
    }

    private static int[] shuffle(int[] arr, int k) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        int base = N;

        for (int i = k; i >= 0; i--) {
            int cnt = (int) Math.pow(2, i);

            int[] cur = new int[N];
            int idx = 0;
            for (int j = base - cnt; j < base; j++) {
                cur[idx++] = copy[j];
            }
            for (int j = 0; j < N; j++) {
                if (base - cnt <= j && j < base) continue;
                cur[idx++] = copy[j];
            }
            base = cnt;
            copy = cur;
        }
        return copy;
    }

    private static boolean isSame(int[] arr) {
        for (int i = 0; i < N; i++) {
            if (arr[i] != cards[i]) return false;
        }
        return true;
    }
}