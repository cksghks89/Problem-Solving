/*
    Boj 1916. 최소비용 구하기
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1916_최소비용구하기 {
    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(dist[dest]);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.to]){
                continue;
            }
            visited[cur.to] = true;

            for(int i = 0; i < graph.get(cur.to).size(); i++){
                Node adjNode = graph.get(cur.to).get(i);

                if(dist[adjNode.to] > cur.weight + adjNode.weight){
                    dist[adjNode.to] = cur.weight + adjNode.weight;
                    pq.offer(new Node(adjNode.to, dist[adjNode.to]));
                }
            }
        }
    }
}
