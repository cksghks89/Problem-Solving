/*
    Boj 1516. 게임 개발
    level. gold 3
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1516_게임개발 {
    static int N;
    static ArrayList<Integer>[] graph;
    static int[] cost;
    static int[] degree;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        cost = new int[N + 1];
        degree = new int[N + 1];
        result = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int cur = Integer.parseInt(st.nextToken());
                if (cur == -1) break;

                graph[cur].add(i);
                degree[i]++;
            }
        }

        int[] ans = topologySort();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append('\n');
        }
        System.out.print(sb.toString());
    }

    static int[] topologySort() {
        int[] maxCosts = new int[N + 1];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
                maxCosts[i] = cost[i];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result = Math.max(result, maxCosts[cur]);

            for (int next : graph[cur]) {
                maxCosts[next] = Math.max(maxCosts[next], maxCosts[cur] + cost[next]);
                if (--degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return maxCosts;
    }
}
