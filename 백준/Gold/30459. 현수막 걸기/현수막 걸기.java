import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, R;
    private static int[] piles;
    private static int[] heights;

    private static double answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 모든 말뚝 2개 선택
        // 2. 이분탐색으로 최대가 될 수 있는 깃대 선택
        // 3. 가능한 것이 있다면 저장

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        piles = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        solve();

        if (answer == -1.0) {
            System.out.println(-1);
        } else {
            System.out.printf("%.1f", answer);
        }
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int width = piles[j] - piles[i];

                int low = 0;
                int high = M - 1;

                while (low <= high) {
                    int mid = (low + high) / 2;

                    double area = width * heights[mid] / 2.0;
                    if (area <= R) {
                        low = mid + 1;
                        answer = Math.max(answer, area);
                    } else {
                        high = mid - 1;
                    }
                }
            }
        }
    }
}