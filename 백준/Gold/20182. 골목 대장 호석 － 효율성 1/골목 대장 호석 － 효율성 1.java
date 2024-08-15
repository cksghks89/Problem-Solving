import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

public class Main {
    static int[] dist;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int A = parseInt(st.nextToken());
        int B = parseInt(st.nextToken());
        int C = parseInt(st.nextToken());

        dist = new int[N + 1];
        graph.add(null);
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<>());

        int u, v, w;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            u = parseInt(st.nextToken());
            v = parseInt(st.nextToken());
            w = parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        System.out.println(binSearch(A, B, C));
        br.close();
    }


    static int binSearch(int start, int end, int C) {
        int low = 1;
        int high = 21;
        int mid;
        int answer = -1;

        while (low <= high) {
            mid = (low + high) / 2;

            if (dijkstra(start, end, C, mid)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }


    static boolean dijkstra(int start, int end, int C, int x) {
        Arrays.fill(dist, MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.w));
        dist[start] = 0;
        pq.offer(new Node(start, dist[start]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.v == end) return dist[cur.v] <= C;

            if (dist[cur.v] < cur.w) continue;

            for (Node next : graph.get(cur.v)) {
                if (next.w > x) continue;

                if (dist[next.v] < dist[cur.v] + next.w) continue;

                dist[next.v] = dist[cur.v] + next.w;
                pq.offer(new Node(next.v, dist[next.v]));
            }
        }

        return false;
    }

    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}