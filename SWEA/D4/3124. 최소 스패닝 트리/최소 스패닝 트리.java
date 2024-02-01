import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int V, E;
    static ArrayList<int[]> edge;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            edge = new ArrayList<>();
            parent = new int[V + 1];
            for (int i = 0; i <= V; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                edge.add(new int[]{a, b, w});
            }

            edge.sort((o1, o2) -> o1[2] - o2[2]);

            long result = 0;
            for (int[] cur : edge) {
                if (union(cur[0], cur[1])) {
                    result += cur[2];
                }
            }

            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb.toString());
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;
        parent[rootB] = rootA;
        return true;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
