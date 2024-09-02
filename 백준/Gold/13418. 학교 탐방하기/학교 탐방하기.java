import java.util.*;
import java.io.*;

public class Main {
    private static int N, M;
    private static List<int[]> edge;

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edge = new ArrayList<>();

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edge.add(new int[]{A, B, C});
        }

        parent = new int[N + 1];

        int zero = mst(0);
        int one = mst(1);

        System.out.println(zero - one);
    }

    private static int mst(int order) {
        for (int i = 0; i <= N; i++) parent[i] = i;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (order == 0) {
                return o1[2] - o2[2];
            } else {
                return o2[2] - o1[2];
            }
        });

        for (int i = 0; i < edge.size(); i++) {
            int[] e = edge.get(i);
            pq.offer(new int[]{e[0], e[1], e[2]});
        }

        int zero = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            boolean result = union(cur[0], cur[1]);
            if (result) {
                zero += cur[2] == 0 ? 1 : 0;
            }
        }

        return zero * zero;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;
        parent[pa] = pb;
        return true;
    }
}