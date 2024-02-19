import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i][0] = i;
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]);

        int[] result = new int[N];
        int cnt = 0;
        int prev = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            result[arr[i][0]] = cnt++;
            if (prev == arr[i][1]) {
                result[arr[i][0]] -= 1;
                cnt--;
            }
            prev = arr[i][1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(result[i]).append(' ');

        System.out.println(sb);
    }
}
