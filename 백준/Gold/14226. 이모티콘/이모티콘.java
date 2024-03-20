import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());

        int answer = dijkstra();

        System.out.println(answer);
    }

    private static int dijkstra() {
        // 0 : 이모티콘 개수, 1 : 클립보드 개수, 2 : 시간
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.offer(new int[]{1, 0, 0});

        boolean[][] visited = new boolean[3000][3000];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] >= 3000 || cur[1] >= 3000 || visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (cur[0] == S) {
                return cur[2];
            }

            pq.offer(new int[]{cur[0], cur[0], cur[2] + 1});
            pq.offer(new int[]{cur[0] + cur[1], cur[1], cur[2] + 1});

            if (cur[0] - 1 >= 1) {
                pq.offer(new int[]{cur[0] - 1, cur[1], cur[2] + 1});
            }
        }
        return 0;
    }
}
