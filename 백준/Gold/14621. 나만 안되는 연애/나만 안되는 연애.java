import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static char[] gender;
    private static PriorityQueue<int[]> pq;

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        gender = new char[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) gender[i] = st.nextToken().charAt(0);
        pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{u, v, d});
        }

        int answer = mst();

        System.out.println(answer);
    }

    private static int mst() {
        int totalCost = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (gender[cur[0]] == gender[cur[1]]) continue;

            boolean result = union(cur[0], cur[1]);

            if (result) {
                totalCost += cur[2];
            }
        }

        int group = find(1);
        for (int i = 1; i <= N; i++) {
            if (group != find(i)) return -1;
        }

        return totalCost;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) return false;
        parent[py] = px;
        return true;
    }
}
