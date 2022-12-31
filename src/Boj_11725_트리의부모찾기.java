/*
    Boj 11725. 트리의 부모 찾기
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_11725_트리의부모찾기 {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        result = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        findParent(N);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void findParent(int N) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        boolean[] visited = new boolean[N + 1];

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;

            for (int i = 0; i < graph.get(cur).size(); i++) {
                if(visited[graph.get(cur).get(i)]){
                    continue;
                }
                result[graph.get(cur).get(i)] = cur;
                queue.offer(graph.get(cur).get(i));
            }
        }
    }
}
