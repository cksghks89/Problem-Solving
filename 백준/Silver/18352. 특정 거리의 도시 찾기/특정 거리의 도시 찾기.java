import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, K, X;
    private static List<Integer>[] graph;

    private static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
        }

        bfs();

        if (answer.isEmpty()) {
            System.out.println(-1);
        } else {
            answer.sort((o1, o2) -> o1 - o2);
            StringBuilder sb = new StringBuilder();
            for (int cur : answer) {
                sb.append(cur).append('\n');
            }

            System.out.println(sb);
        }
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{X, 0});

        boolean[] visited = new boolean[N + 1];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            if (cur[1] == K) {
                answer.add(cur[0]);
                continue;
            }

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int next = graph[cur[0]].get(i);
                if (!visited[next]) {
                    queue.offer(new int[]{next, cur[1] + 1});
                }
            }
        }
    }
}
