import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static PriorityQueue<int[]> pq;
    private static List<int[]>[] graph;

    private static int start, end;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화 --------- start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]);
        graph = new List[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.offer(new int[]{A, B, C});
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        // 입력 및 초기화 --------- end

        makeMst();
        int answer = bfs();

        System.out.println(answer);
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, Integer.MAX_VALUE});

        boolean[] visited = new boolean[N + 1];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            if (cur[0] == end) {
                return cur[1];
            }

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);
                if (!visited[next[0]]) {
                    queue.offer(new int[]{next[0], Math.min(cur[1], next[1])});
                }
            }
        }
        return 0;
    }

    private static void makeMst() {
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            boolean checkUnion = union(cur[0], cur[1]);

            if (checkUnion) {
                graph[cur[0]].add(new int[]{cur[1], cur[2]});
                graph[cur[1]].add(new int[]{cur[0], cur[2]});
            }
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) return false;
        parent[px] = py;
        return true;
    }
}
