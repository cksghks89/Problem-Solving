/*
    Boj 2623. 음악 프로그램
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

public class Boj_2623_음악프로그램 {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        inDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            if (cnt == 0) continue;

            int pre = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt - 1; j++) {
                int post = Integer.parseInt(st.nextToken());
                graph[pre].add(post);
                inDegree[post]++;
                pre = post;
            }
        }

        ArrayList<Integer> result = topologySort();
        StringBuilder sb = new StringBuilder();
        if (result.size() == N) {
            for (int n : result) {
                sb.append(n).append('\n');
            }
        } else {
            sb.append(0).append('\n');
        }
        System.out.print(sb.toString());

    }

    static ArrayList<Integer> topologySort() {
        ArrayList<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            order.add(cur);

            for (int next : graph[cur]) {
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return order;
    }
}
