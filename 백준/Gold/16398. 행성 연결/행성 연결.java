import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    private static int N;
    private static int[][] mat;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        mat = new int[N][N];

        for (int i = 0; i < N; i++) {
            mat[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        long answer = mst();
        System.out.println(answer);
    }

    private static long mst() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{0, 0});

        boolean[] visited = new boolean[N];

        long cost = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            cost += cur[1];

            for (int i = 0; i < N; i++) {
                if (i == cur[0]) continue;
                if (visited[i]) continue;
                pq.offer(new int[]{i, mat[cur[0]][i]});
            }
        }

        return cost;
    }
}
