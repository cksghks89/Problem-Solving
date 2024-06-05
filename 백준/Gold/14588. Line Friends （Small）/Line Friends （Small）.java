import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] lines;
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        lines = new int[N + 1][2];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j || !isFriend(lines[i], lines[j])) continue;
                graph[i].add(j);
                graph[j].add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int friendsDistance = getFriendsDistance(A, B);
            sb.append(friendsDistance).append('\n');
        }

        System.out.println(sb);
    }

    private static int getFriendsDistance(int A, int B) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{A, 0});

        boolean[] visited = new boolean[N + 1];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;

            if (cur[0] == B) {
                return cur[1];
            }

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int next = graph[cur[0]].get(i);
                if (!visited[next]) {
                    queue.offer(new int[]{next, cur[1] + 1});
                }
            }
        }

        return -1;
    }

    private static boolean isFriend(int[] f1, int[] f2) {
        if (f1[0] <= f2[0] && f2[0] <= f1[1]) return true;
        if (f1[0] <= f2[1] && f2[1] <= f1[1]) return true;
        if (f2[0] <= f1[0] && f1[0] <= f2[1]) return true;
        if (f2[0] <= f1[1] && f1[1] <= f2[1]) return true;
        return false;
    }
}
