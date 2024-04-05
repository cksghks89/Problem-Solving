import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static List<Integer>[] graphOut;
    private static List<Integer>[] graphIn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graphOut = new List[N + 1];
        graphIn = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            graphOut[i] = new ArrayList<>();
            graphIn[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graphOut[a].add(b);
            graphIn[b].add(a);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int out = bfs(i, 0);
            int in = bfs(i, 1);

            sb.append(N - (in + out + 1)).append('\n');
        }

        System.out.println(sb);
    }

    private static int bfs(int id, int type) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(id);

        List<Integer>[] curGraph;
        if (type == 0) {
            curGraph = graphOut;
        } else {
            curGraph = graphIn;
        }

        boolean[] visited = new boolean[N + 1];

        int cnt = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visited[cur]) continue;
            visited[cur] = true;
            cnt += 1;

            for (int i = 0; i < curGraph[cur].size(); i++) {
                if (!visited[curGraph[cur].get(i)]) {
                    queue.offer(curGraph[cur].get(i));
                }
            }
        }

        return cnt;
    }

}
