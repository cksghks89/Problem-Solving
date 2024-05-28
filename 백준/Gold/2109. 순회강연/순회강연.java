import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static boolean[] memo;
    private static int[][] lecture;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        lecture = new int[n][2];
        memo = new boolean[10001];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lecture[i][0] = Integer.parseInt(st.nextToken());
            lecture[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lecture, (o1, o2) -> o2[0] - o1[0]);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer += insert(i);
        }

        System.out.println(answer);
    }

    private static int insert(int idx) {
        for (int i = lecture[idx][1]; i >= 1; i--) {
            if (memo[i]) continue;
            memo[i] = true;
            return lecture[idx][0];
        }
        return 0;
    }

}
