/*
    Boj 1774. 우주신과의 교감
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1774_우주신과의교감 {
    static int N, M;
    static PriorityQueue<Edge> edge;
    static int[][] nodes;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new int[N + 1][];
        parent = new int[N + 1];
        edge = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodes[i] = new int[]{x, y};
            parent[i] = i;
        }

        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for (int i = 1; i <= N - 1; i++) {
            for (int j = i + 1; j <= N; j++) {
                edge.add(new Edge(i, j, getDist(i, j)));
            }
        }

        System.out.printf("%.2f", mst());
    }

    static double mst() {
        double result = 0;
        while (!edge.isEmpty()) {
            Edge cur = edge.poll();

            int a = cur.a;
            int b = cur.b;

            if (union(a, b)) {
                result += cur.dist;
            }
        }
        return result;
    }

    static double getDist(int a, int b) {
        return Math.sqrt(Math.pow(nodes[a][0] - nodes[b][0], 2) + Math.pow(nodes[a][1] - nodes[b][1], 2));
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parent[rootB] = rootA;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int a, b;
        double dist;

        public Edge(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.dist < o.dist) {
                return -1;
            } else if (this.dist > o.dist) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
