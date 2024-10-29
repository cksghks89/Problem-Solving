import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] A = new int[N][2];
        for (int i = 0; i < N; i++) {
            A[i][0] = i;
            A[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A, (o1, o2) -> o1[1] - o2[1]);

        int[] ansArr = new int[N];
        for (int i = 0; i < N; i++) {
            ansArr[A[i][0]] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(ansArr[i]).append(' ');
        System.out.println(sb);
    }
}
