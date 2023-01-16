/*
    Boj 11779. 최소 비용 구하기 2
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_11779_최소비용구하기2 {
    static int n, m;
    static ArrayList<ArrayList<int[]>> graph;

    static int start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(src).add(new int[]{dest, weight});
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Node ans = dijkstra(start);

        StringBuilder sb = new StringBuilder();
        sb.append(ans.weight).append("\n");
        sb.append(ans.sequence.size()).append("\n");
        for(int n : ans.sequence){
            sb.append(n).append(" ");
        }

        System.out.println(sb.toString());
    }

    static Node dijkstra(int s) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);
        Node start = new Node(s, 0);
        start.sequence.add(s);
        pq.offer(start);
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.to == end){
                return cur;
            }

            for (int i = 0; i < graph.get(cur.to).size(); i++) {
                int[] next = graph.get(cur.to).get(i);
                int nextIdx = next[0];
                int nextWeight = next[1];

                if (dist[nextIdx] > cur.weight + nextWeight) {
                    dist[nextIdx] = cur.weight + nextWeight;

                    Node nextNode = new Node(nextIdx, dist[nextIdx]);
                    nextNode.sequence.addAll(cur.sequence);
                    nextNode.sequence.add(nextIdx);

                    pq.offer(nextNode);
                }
            }
        }
        return null;
    }

    static class Node {
        int to;
        int weight;
        ArrayList<Integer> sequence;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
            this.sequence = new ArrayList<>();
        }
    }
}
