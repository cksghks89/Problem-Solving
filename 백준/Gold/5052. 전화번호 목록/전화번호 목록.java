import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static class Node {
        int num;
        Node[] child;
        boolean isEnd;

        public Node(int num) {
            this.num = num;
            this.isEnd = false;
            this.child = new Node[10];
        }
    }
    private static Node root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            root = new Node(-1);
            int n = Integer.parseInt(br.readLine());
            boolean result = true;

            for (int i = 0; i < n; i++) {
                if (!insert(br.readLine(), 0, root, false)) {
                    result = false;
                }
            }

            if (result) sb.append("YES").append('\n');
            else sb.append("NO").append('\n');
        }

        System.out.println(sb);
    }

    private static boolean insert(String arr, int cnt, Node node, boolean created) {
        if (node.isEnd) return false;
        if (cnt == arr.length()) {
            node.isEnd = true;
            return created;
        }

        int cur = arr.charAt(cnt) - '0';

        if (node.child[cur] == null) {
            node.child[cur] = new Node(cur);
            return insert(arr, cnt + 1, node.child[cur], true);
        } else {
            return insert(arr, cnt + 1, node.child[cur], created);
        }
    }
}
