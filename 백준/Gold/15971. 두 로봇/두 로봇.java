import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, s, e;
    private static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
  
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph[a].add(new int[]{b, w});
            graph[b].add(new int[]{a, w});
        }

        int answer = getDist();
        System.out.println(answer);
    }
   
    private static int getDist() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{s, 0, 0});
        
        boolean [] visited = new boolean [N + 1];
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;
            
            if (cur[0] == e) { 
                return cur[1] - cur[2];
            }
            
            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);
 
                if (!visited[next[0]]) {
                    queue.offer(new int[]{next[0], cur[1] + next[1], Math.max(cur[2], next[1])});
                }
            }
        }
        return -1;
    }
}