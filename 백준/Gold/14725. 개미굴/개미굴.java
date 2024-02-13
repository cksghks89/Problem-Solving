import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        String feed;
        List<Node> child;

        public Node(String feed, List<Node> child) {
            this.feed = feed;
            this.child = child;
        }
    }

    private static int N;
    private static Node root;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        root = new Node("ROOT", new ArrayList<>());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());

            Node cur = root;
            for (int j = 0; j < t; j++) {
                String input = st.nextToken();
                cur = insert(cur, input);
            }
        }

        for (int i = 0; i < root.child.size(); i++) {
            sort(root);
            dfs(root.child.get(i), 0);
        }

        System.out.println(sb);
    }

    private static Node insert(Node cur, String input) {
        for (int i = 0; i < cur.child.size(); i++) {
            Node nx = cur.child.get(i);
            if (nx.feed.equals(input)) {
                return nx;
            }
        }

        Node newNode = new Node(input, new ArrayList<>());
        cur.child.add(newNode);
        return newNode;
    }

    private static void dfs(Node cur, int depth) {
        for (int i = 0; i < 2 * depth; i++) {
            sb.append('-');
        }
        sb.append(cur.feed).append('\n');

        for (int i = 0; i < cur.child.size(); i++) {
            sort(cur);
            dfs(cur.child.get(i), depth + 1);
        }
    }

    private static void sort(Node cur) {
        cur.child.sort((o1, o2) -> o1.feed.compareTo(o2.feed));
    }
}
