/*
    Boj 1948. 임계경로
    level. platinum 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1948_임계경로 {
    static int n, m;
    static ArrayList<int[]>[] graph;
    static ArrayList<int[]>[] rGraph;
    static int[] inDegree;
    static int s, e;
    static Set<Edge> set = new HashSet<>();
    static int[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        rGraph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) rGraph[i] = new ArrayList<>();
        inDegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph[s].add(new int[]{e, m});
            rGraph[e].add(new int[]{s, m});
            inDegree[e]++;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        topologySort();
        reverseBfs();

        System.out.println(costs[e]);
        System.out.println(set.size());
    }

    static void topologySort() {
        costs = new int[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(s);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int[] next : graph[cur]) {
                costs[next[0]] = Math.max(costs[next[0]], costs[cur] + next[1]);
                if (--inDegree[next[0]] == 0) {
                    queue.offer(next[0]);
                }
            }
        }
    }

    static void reverseBfs() {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(e);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int[] next : rGraph[cur]) {
                if (costs[cur] - next[1] == costs[next[0]]) {
                    set.add(new Edge(cur, next[0]));
                    if (!visited[next[0]]) {
                        queue.offer(next[0]);
                        visited[next[0]] = true;
                    }
                }
            }
        }
    }

    static class Edge {
        int s, e;

        public Edge(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Edge) {
                Edge oo = (Edge) o;
                return this.s == oo.s && this.e == oo.e;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = 31 * Integer.hashCode(s);
            return result * 31 + Integer.hashCode(e);
        }
    }
}
