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
    private static int[] friendCnt = new int[SIZE];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            F = Integer.parseInt(br.readLine());
            nameMap.clear();

            for (int i = 0; i < SIZE; i++) {
                parent[i] = i;
            }

            int nameCnt = 0;

            for (int i = 0; i < F; i++) {
                String[] input = br.readLine().split(" ");
                if (!nameMap.containsKey(input[0])) {
                    friendCnt[nameCnt] = 1;
                    nameMap.put(input[0], nameCnt++);
                }
                if (!nameMap.containsKey(input[1])) {
                    friendCnt[nameCnt] = 1;
                    nameMap.put(input[1], nameCnt++);
                }

                int n1 = find(nameMap.get(input[0]));
                int n2 = find(nameMap.get(input[1]));

                if (n1 == n2) {
                    sb.append(friendCnt[n1]).append('\n');
                    continue;
                }

                int n1Cnt = friendCnt[n1];
                int n2Cnt = friendCnt[n2];

                union(n1, n2);

                friendCnt[n1] = n1Cnt + n2Cnt;

                sb.append(friendCnt[n1]).append('\n');
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
        parent[py] = px;
    }
}
