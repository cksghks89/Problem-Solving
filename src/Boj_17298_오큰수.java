/*
    Boj 17298. 오큰수
    level. gold 4
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Boj_17298_오큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Stack<int[]> stack = new Stack<>();
        int[] rtn = new int[N];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (stack.isEmpty()) {
                stack.push(new int[]{i, A[i]});
                continue;
            }

            while (!stack.isEmpty() && stack.peek()[1] < A[i]) {
                int[] cur = stack.pop();
                rtn[cur[0]] = A[i];
            }

            stack.push(new int[]{i, A[i]});
        }

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            rtn[cur[0]] = -1;
        }
        for (int cur : rtn) {
            sb.append(cur).append(' ');
        }
        System.out.print(sb);
    }
}
