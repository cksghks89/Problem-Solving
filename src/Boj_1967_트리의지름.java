/*
    Boj 1967. 트리의 지름
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1967_트리의지름 {
    static int n;
    static ArrayList<ArrayList<int[]>> graph;
    static boolean[] visited;

    static int maxCost = Integer.MIN_VALUE;
    static int maxIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(p).add(new int[]{c, w});
            graph.get(c).add(new int[]{p, w});
        }

        visited[1] = true;
        dfs(1, 0);

        maxCost = Integer.MIN_VALUE;
        Arrays.fill(visited, false);
        visited[maxIdx] = true;
        dfs(maxIdx, 0);

        System.out.println(maxCost);
    }

    static void dfs(int x, int dist) {
        if (dist > maxCost) {
            maxCost = dist;
            maxIdx = x;
        }

        for (int i = 0; i < graph.get(x).size(); i++) {
            int[] cur = graph.get(x).get(i);

            int to = cur[0];
            int weight = cur[1];

            if(!visited[to]){
                visited[to] = true;
                dfs(to, dist + weight);
                visited[to] = false;
            }
        }
    }
}
