/*
    Boj 2887. 행성 터널
    level. platinum 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_2887_행성터널 {
    static int N;
    static ArrayList<ArrayList<Edge>> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(i,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }

        Arrays.sort(nodes, (o1, o2) -> o1.x - o2.x);
        for (int i = 1; i < nodes.length; i++) {
            Node a = nodes[i - 1];
            Node b = nodes[i];
            edges.get(a.id).add(new Edge(b.id, b.x - a.x));
            edges.get(b.id).add(new Edge(a.id, b.x - a.x));
        }
        Arrays.sort(nodes, (o1, o2) -> o1.y - o2.y);
        for (int i = 1; i < nodes.length; i++) {
            Node a = nodes[i - 1];
            Node b = nodes[i];
            edges.get(a.id).add(new Edge(b.id, b.y - a.y));
            edges.get(b.id).add(new Edge(a.id, b.y - a.y));
        }
        Arrays.sort(nodes, (o1, o2) -> o1.z - o2.z);
        for (int i = 1; i < nodes.length; i++) {
            Node a = nodes[i - 1];
            Node b = nodes[i];
            edges.get(a.id).add(new Edge(b.id, b.z - a.z));
            edges.get(b.id).add(new Edge(a.id, b.z - a.z));
        }

        System.out.println(prim());
    }

    static int prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);
        pq.offer(new Edge(0, 0));
        boolean[] visited = new boolean[N];

        int dist = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }

            visited[cur.to] = true;
            dist += cur.weight;

            for (int i = 0; i < edges.get(cur.to).size(); i++) {
                pq.offer(edges.get(cur.to).get(i));
            }
        }
        return dist;
    }

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node {
        int id;
        int x, y, z;

        public Node(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
