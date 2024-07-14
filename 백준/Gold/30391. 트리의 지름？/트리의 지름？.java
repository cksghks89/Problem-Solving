import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, K;
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        // 차수가 K 이하가 되도록 하면서 지름 최소
        // 포화 트리로 구성하기.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        int node = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            int degree = K - 1;     // 부모와의 차수 하나를 제거
            if (cur == 1) degree += 1;

            for (int i = 0; i < degree; i++) {
                if (node + 1 > N) break;
                graph[cur].add(++node);
                queue.offer(node);
            }
        }

        boolean[] visited = new boolean[N + 1];
        queue.clear();
        queue.offer(1);

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visited[cur]) continue;
            visited[cur] = true;

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                sb.append(cur).append(" ").append(next).append('\n');
                queue.offer(next);
            }
        }

        System.out.println(sb);
    }
}
