/*
    Boj 1647. 도시 분할 계획
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1647_도시분할계획 {
    static int N, M;
    static ArrayList<ArrayList<int[]>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 양방향 그래프 구조 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new int[]{B, C});
            graph.get(B).add(new int[]{A, C});
        }

        System.out.println(prim());
    }

    static int prim() {
        ArrayList<Integer> weights = new ArrayList<>();
        int weightSum = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        pq.offer(new int[]{1, 0});

        boolean[] visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;

            weights.add(cur[1]);
            weightSum += cur[1];

            for (int i = 0; i < graph.get(cur[0]).size(); i++) {
                int[] n = graph.get(cur[0]).get(i);
                if (visited[n[0]]) {
                    continue;
                }

                pq.offer(new int[]{n[0], n[1]});
            }
        }

        weights.sort((x, y) -> y - x);
        return weightSum - weights.get(0);
    }
}
