import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        int id;
        int row;
        int col;
        Node left;
        Node right;
        Node parent;

        public Node(int id) {
            this.id = id;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private static int N;
    private static Node[] nodes;

    private static int colCnt = 1;
    private static int maxRow = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (l != -1) {
                nodes[id].left = nodes[l];
                nodes[l].parent = nodes[id];
            }
            if (r != -1) {
                nodes[id].right = nodes[r];
                nodes[r].parent = nodes[id];
            }
        }

        int rootId = getRootId();
        bfs(rootId);
        inOrder(nodes[rootId]);

        int[][] minMax = new int[maxRow + 1][2];
        for (int i = 1; i <= maxRow; i++) minMax[i][0] = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            Node curNode = nodes[i];
            minMax[curNode.row][0] = Math.min(minMax[curNode.row][0], curNode.col);
            minMax[curNode.row][1] = Math.max(minMax[curNode.row][1], curNode.col);
        }

        int colNum = -1;
        int width = 0;
        for (int i = 1; i <= maxRow; i++) {
            int curWidth = minMax[i][1] - minMax[i][0] + 1;
            if (curWidth > width) {
                colNum = i;
                width = curWidth;
            }
        }

        System.out.println(colNum + " " + width);
    }

    private static int getRootId() {
        int id = nodes[1].id;
        while (nodes[id].parent != null) {
            id = nodes[id].parent.id;
        }
        return id;
    }

    private static void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        node.col = colCnt++;
        inOrder(node.right);
    }

    private static void bfs(int rootId) {
        nodes[rootId].row = 1;

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(nodes[rootId]);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            maxRow = Math.max(maxRow, cur.row);

            if (cur.left != null) {
                cur.left.row = cur.row + 1;
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                cur.right.row = cur.row + 1;
                queue.offer(cur.right);
            }
        }
    }
}
