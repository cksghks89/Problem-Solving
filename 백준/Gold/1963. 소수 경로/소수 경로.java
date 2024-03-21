import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static boolean[] prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        prime = new boolean[10001];
        Arrays.fill(prime, true);
        for (int i = 2; i < 10001; i++) {
            if (!prime[i]) continue;
            prime[i] = true;
            for (int j = 2 * i; j < 10001; j += i) {
                prime[j] = false;
            }
        }

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            int result = bfs(n1, n2);

            if (result == -1) sb.append("Impossible").append('\n');
            else sb.append(result).append('\n');
        }

        System.out.println(sb);
    }

    private static int bfs(int n1, int n2) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{n1, 0});

        boolean[] visited = new boolean[10001];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            if (cur[0] == n2) return cur[1];

            for (int i = 0; i < 4; i++) {
                int[] nums = getNum(cur[0], i);
                for (int j = 0; j < 10; j++) {
                    if (nums[j] >= 1000 && !visited[nums[j]] && prime[nums[j]]) {
                        queue.offer(new int[]{nums[j], cur[1] + 1});
                    }
                }
            }
        }
        return -1;
    }

    private static int[] getNum(int num, int d) {
        // d번째 자리수를 0 ~ 9까지로 변환
        int[] result = new int[10];
        for (int i = 0; i < 10; i++) {
            int sub = num % ((int) Math.pow(10, d + 1)) - num % ((int) Math.pow(10, d));
            result[i] = num - sub + i * (int) Math.pow(10, d);
        }
        return result;
    }
}
