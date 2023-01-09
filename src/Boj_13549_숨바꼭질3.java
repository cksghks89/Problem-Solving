/*
    Boj 13549. 숨바꼭질 3
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_13549_숨바꼭질3 {
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    static int bfs() {
        if (N >= K) {
            return N - K;
        }

        int SIZE = 200001;

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        queue.offer(new int[]{N, 0});

        boolean[] visited = new boolean[SIZE];
        int result = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int pos = cur[0];
            int cnt = cur[1];

            if (pos == K) {
                result = cnt;
                break;
            }

            if (visited[pos]) {
                continue;
            }
            visited[pos] = true;

            if (pos * 2 < SIZE && !visited[pos * 2]) {
                queue.offer(new int[]{pos * 2, cnt});
            }

            if (pos - 1 >= 0 && !visited[pos - 1]) {
                queue.offer(new int[]{pos - 1, cnt + 1});
            }

            if (pos + 1 < SIZE && !visited[pos + 1]) {
                queue.offer(new int[]{pos + 1, cnt + 1});
            }
        }

        return result;
    }
}
