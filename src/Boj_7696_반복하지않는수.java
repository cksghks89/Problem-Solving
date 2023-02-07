/*
    Boj 7696. 반복하지 않는 수
    level. silver 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_7696_반복하지않는수 {
    static int cnt;
    static int n;
    static boolean[] visited;
    static int[] arr;
    static boolean allReturn = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] accSum = new int[11];
        accSum[0] = 1;
        int idx = 9;
        for (int i = 1; i < 10; i++) {
            accSum[i] = accSum[i - 1] * idx;

            if (i == 1) {
                continue;
            }
            idx -= 1;
        }

        StringBuilder sb = new StringBuilder();
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            allReturn = false;
            visited = new boolean[11];
            cnt = 0;

            for (int i = 1; i < 10; i++) {
                if (n <= cnt + accSum[i]) {
                    idx = i;
                    break;
                } else {
                    cnt += accSum[i];
                }
            }

            arr = new int[idx];
            perm(0, idx);

            for (int cur : arr) {
                sb.append(cur);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void perm(int cur, int idx) {
        if (cur == idx) {
            cnt += 1;
            if (cnt == n) {
                allReturn = true;
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (allReturn) {
                return;
            }
            if ((i == 0 && cur == 0) || visited[i]) {
                continue;
            }

            visited[i] = true;
            arr[cur] = i;
            perm(cur + 1, idx);
            visited[i] = false;
        }
    }
}
