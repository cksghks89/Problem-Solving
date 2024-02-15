import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static List<Integer>[] graph;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        int[] result = recur(1);

        System.out.println(Math.min(result[0], result[1]));
    }

    private static int[] recur(int id) {
        visited[id] = true;
        int childCnt = 0;
        for (int i = 0; i < graph[id].size(); i++) {
            if (!visited[graph[id].get(i)]) childCnt += 1;
        }

        if (childCnt == 0) {
            return new int[]{0, 1};
        }

        int allOne = 0;
        int min = 0;
        for (int i = 0; i < graph[id].size(); i++) {
            int nx = graph[id].get(i);
            if (visited[nx]) continue;
            int[] sub = recur(nx);
            allOne += sub[1];
            min += Math.min(sub[0], sub[1]);
        }

        return new int[]{allOne, min + 1};
    }
}
