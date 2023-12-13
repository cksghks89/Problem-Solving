import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        int[] visited = new int[200001];
        Arrays.fill(visited, -1);

        pq.offer(new int[]{N, 0});
        visited[N] = N;

        int resultTime = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == K) {
                resultTime = cur[1];
                break;
            }

            if (isInRange(cur[0] + 1) && visited[cur[0] + 1] == -1) {
                pq.offer(new int[]{cur[0] + 1, cur[1] + 1});
                visited[cur[0] + 1] = cur[0];
            }
            if (isInRange(cur[0] - 1) && visited[cur[0] - 1] == -1) {
                pq.offer(new int[]{cur[0] - 1, cur[1] + 1});
                visited[cur[0] - 1] = cur[0];
            }
            if (isInRange(cur[0] * 2) && visited[cur[0] * 2] == -1) {
                pq.offer(new int[]{cur[0] * 2, cur[1] + 1});
                visited[cur[0] * 2] = cur[0];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(resultTime).append('\n');

        int[] resultSeq = new int[resultTime + 1];
        int prev = K;
        resultSeq[resultTime] = K;
        for (int i = resultTime - 1; i >= 0; i--) {
            resultSeq[i] = visited[prev];
            prev = visited[prev];
        }

        for (int item : resultSeq) sb.append(item).append(' ');

        System.out.println(sb);
    }

    private static boolean isInRange(int x) {
        return 0 <= x && x <= 200000;
    }
}
