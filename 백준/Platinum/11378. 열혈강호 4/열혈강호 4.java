import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static List<Integer>[] graph;
    private static int[] assign;
    private static int[] done;

    private static int visitCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        assign = new int[M + 1];
        done = new int[M + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                int v = Integer.parseInt(st.nextToken());
                graph[i].add(v);
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            visitCount += 1;
            boolean matching = bipartiteMatching(i);
            if (matching) answer += 1;
        }

        for (int i = 1; i <= N; i++) {
            while (K != 0) {
                visitCount += 1;
                if (!bipartiteMatching(i)) break;
                K -= 1;
                answer += 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean bipartiteMatching(int id) {
        for (int i = 0; i < graph[id].size(); i++) {
            int job = graph[id].get(i);
            if (done[job] == visitCount) continue;
            done[job] = visitCount;

            if (assign[job] == 0 || bipartiteMatching(assign[job])) {
                assign[job] = id;
                return true;
            }
        }
        return false;
    }
}
