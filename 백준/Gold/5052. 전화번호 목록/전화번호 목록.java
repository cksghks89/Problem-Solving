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
                int[] arr = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
                if (!insert(arr, 0, root, false)) {
                    result = false;
                }
            }

            if (result) sb.append("YES").append('\n');
            else sb.append("NO").append('\n');
        }

        System.out.println(sb);
    }

    private static boolean insert(int[] arr, int cnt, Node node, boolean created) {
        if (node.isEnd) return false;
        if (cnt == arr.length) {
            node.isEnd = true;
            return created;
        }

        if (node.child[arr[cnt]] == null) {
            node.child[arr[cnt]] = new Node(arr[cnt]);
            return insert(arr, cnt + 1, node.child[arr[cnt]], true);
        } else {
            return insert(arr, cnt + 1, node.child[arr[cnt]], created);
        }
    }
}
