/*
    Boj 17471. 게리맨더링
    level. gold 4
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_17471_게리맨더링 {
    static int N;
    static ArrayList<Integer>[] graph;
    static int[] population;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = Integer.MAX_VALUE;

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            for (int j = 0; j < c; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        subSet(1, new HashSet<>(), new HashSet<>());
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    public static void subSet(int cnt, Set<Integer> area1, Set<Integer> area2) {
        if (cnt == N + 1) {
            if (area1.size() == 0 || area2.size() == 0) return;
            int a1 = bfs(area1);
            int a2 = bfs(area2);
            if (a1 >= 0 && a2 >= 0) {
                result = Math.min(result, Math.abs(a1 - a2));
            }
            return;
        }

        area1.add(cnt);
        subSet(cnt + 1, area1, area2);
        area1.remove(cnt);

        area2.add(cnt);
        subSet(cnt + 1, area1, area2);
        area2.remove(cnt);
    }

    static int bfs(Set<Integer> area1) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(area1.iterator().next());

        int count = 0;
        int populationCnt = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visited[cur]) continue;
            visited[cur] = true;
            count += 1;
            populationCnt += population[cur - 1];

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);

                if (area1.contains(next) && !visited[next]) {
                    queue.offer(next);
                }
            }
        }

        if (count == area1.size()) return populationCnt;
        else return -1;
    }
}
