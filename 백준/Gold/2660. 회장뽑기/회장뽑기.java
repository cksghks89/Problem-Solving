import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) break;

            graph[a].add(b);
            graph[b].add(a);
        }

        int[][] point = new int[N + 1][2];
        int minPoint = Integer.MAX_VALUE;
        int cnt = 0;
        List<Integer> candidate = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            point[i][0] = i;
            point[i][1] = bfs(i);

            if (minPoint > point[i][1]) {
                minPoint = point[i][1];
                cnt = 1;
                candidate.clear();
                candidate.add(i);
            } else if (minPoint == point[i][1]) {
                cnt += 1;
                candidate.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minPoint).append(" ").append(candidate.size()).append('\n');
        for (int cur : candidate) sb.append(cur).append(' ');

        System.out.println(sb);
    }

    private static int bfs(int id) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{id, 0});

        boolean[] visited = new boolean[N + 1];
        int depth = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;
            depth = cur[1];

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int next = graph[cur[0]].get(i);
                if (!visited[next]) {
                    queue.offer(new int[]{next, cur[1] + 1});
                }
            }
        }

        return depth;
    }
}
