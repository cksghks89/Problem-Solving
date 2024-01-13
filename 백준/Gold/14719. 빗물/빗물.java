import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int[] block;
    private static int H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int[] arr = new int[W];
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        // 층 별로 관리
        int answer = 0;
        for (int i = 1; i <= max; i++) {
            // 각 층에서 고이는 빗물 체크
            Deque<Integer> stack = new ArrayDeque<>();
            int curWater = 0;
            for (int j = 0; j < W; j++) {
                if (arr[j] >= i) {
                    if (!stack.isEmpty()) {
                        int cur = stack.pop();
                        curWater += j - cur - 1;
                    }
                    stack.push(j);
                }
            }
            answer += curWater;
        }

        System.out.println(answer);
    }
}
