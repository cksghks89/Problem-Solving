import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int G, P;
    private static int[] parent;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        visited = new boolean[G + 1];

        for (int i = 1; i <= G; i++) parent[i] = i;

        int answer = 0;
        for (int i = 0; i < P; i++) {
            int cur = Integer.parseInt(br.readLine());

            int idx = find(cur);

            if (idx == 0) break;

            visited[idx] = true;
            union(idx - 1, idx);
            answer += 1;
        }

        System.out.println(answer);
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;

        parent[pb] = pa;
    }
}
