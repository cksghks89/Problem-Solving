import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static final int SIZE = 200_002;

    private static int F;
    private static int[] parent = new int[SIZE];
    private static Map<String, Integer> nameMap = new HashMap<>();
    private static Set<Integer>[] friendSet = new Set[SIZE];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < SIZE; i++) friendSet[i] = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            F = Integer.parseInt(br.readLine());
            nameMap.clear();

            for (int i = 0; i < SIZE; i++) {
                parent[i] = i;
                friendSet[i].clear();
            }

            int nameCnt = 0;

            for (int i = 0; i < F; i++) {
                String[] input = br.readLine().split(" ");
                if (!nameMap.containsKey(input[0])) {
                    friendSet[nameCnt].add(nameCnt);
                    nameMap.put(input[0], nameCnt++);
                }
                if (!nameMap.containsKey(input[1])) {
                    friendSet[nameCnt].add(nameCnt);
                    nameMap.put(input[1], nameCnt++);
                }

                int n1 = find(nameMap.get(input[0]));
                int n2 = find(nameMap.get(input[1]));

                if (n1 == n2) {
                    sb.append(friendSet[n1].size()).append('\n');
                    continue;
                }

                Set<Integer> n1Set = friendSet[n1];
                Set<Integer> n2Set = friendSet[n2];

                union(n1, n2);

                if (n1Set.size() < n2Set.size()) {
                    n2Set.addAll(n1Set);
                    n1Set.clear();
                    friendSet[n1] = n2Set;
                } else {
                    n1Set.addAll(n2Set);
                    n2Set.clear();
                    friendSet[n1] = n1Set;
                }

                sb.append(friendSet[n1].size()).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        parent[py] = px;
    }
}
