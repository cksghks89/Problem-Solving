import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.ArrayList;

import java.util.List;

import java.util.PriorityQueue;

import java.util.StringTokenizer;

public class Main {

    private static int N, M, K;

    private static List<Integer> powerPlant;

    private static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(st.nextToken());

        powerPlant = new ArrayList<>();

        graph = new List[N + 1];

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {

            powerPlant.add(Integer.parseInt(st.nextToken()));

        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());

            int v = Integer.parseInt(st.nextToken());

            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v, w});

            graph[v].add(new int[]{u, w});

        }

        int answer = prim();

        System.out.println(answer);

    }

    private static int prim() {

        boolean[] visited = new boolean[N + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        for (int i = 0; i < powerPlant.size(); i++) {

            int cur = powerPlant.get(i);

            visited[cur] = true;

            for (int j = 0; j < graph[cur].size(); j++) {

                int[] next = graph[cur].get(j);

                pq.offer(new int[]{next[0], next[1]});

            }

        }

        int cost = 0;

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            if (visited[cur[0]]) continue;

            visited[cur[0]] = true;

            cost += cur[1];

            for (int i = 0; i < graph[cur[0]].size(); i++) {

                int[] next = graph[cur[0]].get(i);

                if (!visited[next[0]]) {

                    pq.offer(new int[]{next[0], next[1]});

                }

            }

        }

        return cost;

    }

}

