/*
    Boj 16562. 친구비
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_16562_친구비 {
    static int N, M, k;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        List<Cost> costs = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costs.add(new Cost(i, Integer.parseInt(st.nextToken())));
        }
        Collections.sort(costs);

        makeSet();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int totalCost = 0;
        for (Cost cur : costs) {
            if (union(0, cur.id)) {
                totalCost += cur.cost;
            }
        }

        System.out.println(totalCost > k ? "Oh no" : totalCost);
    }

    static void makeSet() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    static int find(int a) {
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        parent[bRoot] = aRoot;
        return true;
    }

    static class Cost implements Comparable<Cost> {
        int id;
        int cost;

        public Cost(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cost o) {
            return this.cost - o.cost;
        }
    }
}
