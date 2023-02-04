/*
    Boj 2493. 탑
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_2493_탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] tower = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        tower[0] = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int num : getList(N, tower)) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString());
    }

    static int[] getList(int N, int[] tower) {
        Stack<Integer> stack = new Stack<>();

        int[] result = new int[N];
        int idx = 0;

        stack.push(0);
        for (int i = 1; i < tower.length; i++) {
            if (tower[i] < tower[stack.peek()]) {
                result[idx++] = stack.peek();
                stack.push(i);
                continue;
            }

            while (tower[i] > tower[stack.peek()]) {
                stack.pop();
            }
            result[idx++] = stack.peek();
            stack.push(i);
        }

        return result;
    }
}
