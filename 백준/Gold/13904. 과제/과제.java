import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] work = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(work, (o1, o2) -> o2[1] - o1[1]);

        boolean[] dayCheck = new boolean[1001];

        int answer = 0;
        for (int i = 0; i < N; i++) {
            boolean isInsert = insertDay(dayCheck, work[i][0]);
            if (isInsert) answer += work[i][1];
        }

        System.out.println(answer);
    }

    private static boolean insertDay(boolean[] dayCheck, int day) {
        for (int i = day; i >= 1; i--) {
            if (dayCheck[i]) continue;
            dayCheck[i] = true;
            return true;
        }

        return false;
    }

}
