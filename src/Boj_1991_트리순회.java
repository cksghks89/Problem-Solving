/*
    Boj 1991. 트리 순회
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj_1991_트리순회 {
    static class Node {
        String id;
        Node left;
        Node right;

        public Node(String id, Node left, Node right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }
    }
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Node root = new Node("A", null, null);
        Map<String, Node> map = new HashMap<>();
        map.put("A", root);

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            String parent = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            Node cur = map.get(parent);

            if(!left.equals(".")){
                cur.left = new Node(left, null, null);
                map.put(left, cur.left);
            }
            if(!right.equals(".")){
                cur.right = new Node(right, null, null);
                map.put(right, cur.right);
            }
        }

        preOrder(root);
        sb.append("\n");
        inOrder(root);
        sb.append("\n");
        postOrder(root);

        System.out.println(sb.toString());

    }

    static void preOrder(Node cur){
        if(cur == null){
            return;
        }
        sb.append(cur.id);
        preOrder(cur.left);
        preOrder(cur.right);
    }

    static void inOrder(Node cur){
        if(cur == null){
            return;
        }
        inOrder(cur.left);
        sb.append(cur.id);
        inOrder(cur.right);
    }

    static void postOrder(Node cur){
        if(cur == null){
            return;
        }
        postOrder(cur.left);
        postOrder(cur.right);
        sb.append(cur.id);
    }
}
