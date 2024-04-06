import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            arr[i] = new int[]{i, Integer.parseInt(br.readLine())};
        }

        Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]);

        int answer = -1;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, arr[i][0] - i);
        }

        System.out.println(answer + 1);
    }
}
