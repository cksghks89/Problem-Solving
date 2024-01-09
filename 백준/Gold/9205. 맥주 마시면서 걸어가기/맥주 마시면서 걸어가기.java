import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] point;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuffer sb = new StringBuffer();
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            point = new int[n + 2][2];
            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                point[i][0] = Integer.parseInt(st.nextToken());
                point[i][1] = Integer.parseInt(st.nextToken());
            }

            boolean result = bfs(0, n + 1);

            if (result) sb.append("happy").append('\n');
            else sb.append("sad").append('\n');
        }

        System.out.println(sb);
    }

    private static boolean bfs(int start, int target) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        boolean[] visited = new boolean[point.length];

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visited[cur]) continue;
            visited[cur] = true;

            if (cur == target) {
                return true;
            }

            for (int i = 0; i < point.length; i++) {
                if (visited[i]) continue;

                if (isPossible(cur, i)) {
                    queue.offer(i);
                }
            }
        }
        return false;
    }

    // point[x] -> point[y] 로 갈 수 있는지 검사
    private static boolean isPossible(int x, int y) {
        int distance = Math.abs(point[x][0] - point[y][0]) + Math.abs(point[x][1] - point[y][1]);
        return distance <= 1000;
    }
}
