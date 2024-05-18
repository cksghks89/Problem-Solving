import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lines = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lines, (o1, o2) -> o1[0] - o2[0]);

        // 다음 시작이 이전 끝보다 크다면 끊어짐

        int totalLength = 0;
        int[] prev = lines[0];
        for (int i = 1; i < N; i++) {
            if (lines[i][0] > prev[1]) {
                totalLength += prev[1] - prev[0];
                prev = lines[i];
            } else {
                prev[1] = Math.max(prev[1], lines[i][1]);
            }
        }

        totalLength += prev[1] - prev[0];

        System.out.println(totalLength);
    }
}
