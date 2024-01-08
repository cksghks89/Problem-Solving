import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, M;
    private static List<Integer>[] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N];

        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            boolean result = dfs(i, 0);

            if (result) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    private static boolean dfs(int x, int cnt) {
        if (cnt == 4) {
            return true;
        }

        for (int i = 0; i < graph[x].size(); i++) {
            int next = graph[x].get(i);

            if (visited[next]) continue;

            visited[x] = true;
            boolean result = dfs(next, cnt + 1);

            if (result) {
                return true;
            }
            visited[x] = false;
        }
        return false;
    }
}
