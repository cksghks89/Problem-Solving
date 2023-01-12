/*
    Boj 12851. 숨바꼭질 2
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_12851_숨바꼭질2 {
    static int N, K;
    static int minTime = Integer.MAX_VALUE;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N > K) {
            System.out.println(N - K + "\n" + 1);
            return;
        }

        bfs(N);
        System.out.println(minTime);
        System.out.println(cnt);
    }

    static void bfs(int N) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{N, 0});

        boolean[] visited = new boolean[K * 2 + 1];
        visited[N] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int idx = cur[0];
            int time = cur[1];
            visited[idx] = true;

            if (idx == K) {
                if (time < minTime) {
                    minTime = time;
                    cnt = 1;
                } else if (time == minTime) {
                    cnt += 1;
                } else {
                    break;
                }
            }

            if (idx < K) {
                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    queue.offer(new int[]{idx - 1, time + 1});
                }
                if (idx + 1 <= 2 * K && !visited[idx + 1]) {
                    queue.offer(new int[]{idx + 1, time + 1});
                }
                if (idx * 2 <= 2 * K && !visited[idx * 2]) {
                    queue.offer(new int[]{idx * 2, time + 1});
                }
            } else if (idx > K && idx - 1 >= 0 && !visited[idx - 1]) {
                queue.offer(new int[]{idx - 1, time + 1});
            }
        }
    }
}
