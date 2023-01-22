/*
    Boj 1208. 부분수열의 합 2
    level. gold 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj_1208_부분수열의합2 {
    static int N, S;
    static ArrayList<Integer> sumListLeft;
    static ArrayList<Integer> sumListRight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] left = new int[N / 2];
        int[] right = new int[N / 2 + N % 2];
        System.arraycopy(arr, 0, left, 0, N / 2);
        System.arraycopy(arr, N / 2, right, 0, N / 2 + N % 2);

        sumListLeft = new ArrayList<>();
        sumListRight = new ArrayList<>();

        dfs(0, 0, left, sumListLeft);
        dfs(0, 0, right, sumListRight);

        Collections.sort(sumListLeft);
        Collections.sort(sumListRight);

        System.out.println(towPointer() - (S == 0 ? 1 : 0));
    }

    static long towPointer() {
        // 2^20 개 * 2^20 개 = 2 ^ 40 개 이므로 int 형일경우 오버플로우가 발생
        long cnt = 0;

        int p1 = 0;
        int p2 = sumListRight.size() - 1;

        while (isInRange(p1, p2)) {
            int cur = sumListLeft.get(p1) + sumListRight.get(p2);
            if (cur == S) {
                // 같은 부분수열이 몇 개 있는지 확인
                int leftCnt = 1;
                int rightCnt = 1;
                while (p1 + 1 < sumListLeft.size()) {
                    if (!sumListLeft.get(p1).equals(sumListLeft.get(p1 + 1))) {
                        break;
                    }
                    p1 += 1;
                    leftCnt += 1;
                }
                while (p2 - 1 >= 0) {
                    if (!sumListRight.get(p2).equals(sumListRight.get(p2 - 1))) {
                        break;
                    }
                    p2 -= 1;
                    rightCnt += 1;
                }
                cnt += (long)leftCnt * rightCnt;
                p1++;
                p2--;
            } else if (cur < S) {
                p1++;
            } else {
                p2--;
            }
        }
        return cnt;
    }

    static boolean isInRange(int p1, int p2) {
        return (0 <= p1 && p1 < sumListLeft.size()) && (0 <= p2 && p2 < sumListRight.size());
    }

    static void dfs(int idx, int sum, int[] arr, ArrayList<Integer> list) {
        if (idx == arr.length) {
            list.add(sum);
            return;
        }

        dfs(idx + 1, sum + arr[idx], arr, list);
        dfs(idx + 1, sum, arr, list);
    }
}
