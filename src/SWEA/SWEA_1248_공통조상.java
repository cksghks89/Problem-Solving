package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_1248_공통조상 {
    static Node[] nodes;

    static class Node {
        int id;
        Node parent;
        ArrayList<Node> child = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V1 = Integer.parseInt(st.nextToken());
            int V2 = Integer.parseInt(st.nextToken());

            nodes = new Node[V + 1];
            for (int i = 1; i <= V; i++) {
                nodes[i] = new Node(i);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                nodes[parent].child.add(nodes[child]);
                nodes[child].parent = nodes[parent];
            }

            int parentIdx = findUnionParent(V1, V2);
            int answer = dfs(parentIdx);

            sb.append("#"+tc+" ").append(parentIdx).append(" ")
                    .append(answer).append("\n");
        }

        System.out.print(sb.toString());
    }

    static int dfs(int idx) {
        if (nodes[idx].child.size() == 0) {
            return 1;
        }

        int rtn = 0;
        for (int i = 0; i < nodes[idx].child.size(); i++) {
            rtn += dfs(nodes[idx].child.get(i).id);
        }

        return rtn + 1;
    }

    static int findUnionParent(int V1, int V2) {
        Set<Integer> parentSet = new HashSet<>();

        Node v1Node = nodes[V1];
        while (v1Node != null) {
            parentSet.add(v1Node.id);
            v1Node = v1Node.parent;
        }

        Node v2Node = nodes[V2];
        while (v2Node != null) {
            if (parentSet.contains(v2Node.id)) {
                return v2Node.id;
            }
            v2Node = v2Node.parent;
        }

        return 1;
    }
}
