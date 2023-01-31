/*
    Boj 4386. 별자리 만들기
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_4386_별자리만들기 {
    static int n;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n];

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            Point p = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            points[i] = p;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = points[i].distance(points[j]);
                graph[i].add(new Node(j, dist));
                graph[j].add(new Node(i, dist));
            }
        }

        double ans = (int)(prim() * 100) / 100.0;
        System.out.println(ans);
    }

    static double prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> {
            int tmp = (int) ((x.weight - y.weight) * 10000);
            return Integer.compare(tmp, 0);
        });
        pq.offer(new Node(0, 0));
        boolean[] visited = new boolean[n];

        double sum = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.id]) {
                continue;
            }
            visited[cur.id] = true;

            sum += Math.sqrt(cur.weight);

            for (int i = 0; i < graph[cur.id].size(); i++) {
                Node node = graph[cur.id].get(i);
                if(visited[node.id]){
                    continue;
                }
                pq.offer(node);
            }
        }
        return sum;
    }

    static class Node {
        int id;
        double weight;

        public Node(int id, double weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double distance(Point p) {
            return Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2);
        }
    }
}
