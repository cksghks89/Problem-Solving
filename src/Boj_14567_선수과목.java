/*
    Boj 14567. 선수 과목
    level. gold 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_14567_선수과목 {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        degree = new int[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            degree[b] += 1;
        }

        ArrayList<int[]> result = topologySort();
        StringBuilder sb = new StringBuilder();
        result.sort((o1, o2) -> o1[0] - o2[0]);
        result.forEach(x -> sb.append(x[1]).append(' '));

        System.out.println(sb.toString());
    }

    static ArrayList<int[]> topologySort() {
        ArrayList<int[]> order = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) queue.offer(new int[]{i, 1});
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            order.add(new int[]{cur[0], cur[1]});

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int next = graph[cur[0]].get(i);
                degree[next] -= 1;
                if (degree[next] == 0) {
                    queue.offer(new int[]{next, cur[1] + 1});
                }
            }
        }
        return order;
    }
}
