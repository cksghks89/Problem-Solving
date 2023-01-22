/*
    Boj 1182. 부분수열의 합
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1182_부분수열의합 {
    static int N, S;
    static int[] arr;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cnt = 0;

        dfs(0, 0);
        if (S == 0) {
            cnt--;
        }
        System.out.println(cnt);
    }

    static void dfs(int idx, int sum) {
        if (idx == arr.length) {
            if (sum == S) {
                cnt++;
            }
            return;
        }

        dfs(idx + 1, sum + arr[idx]);
        dfs(idx + 1, sum);
    }
}
