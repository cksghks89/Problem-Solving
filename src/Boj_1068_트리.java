/*
    Boj 1068 트리
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj_1068_트리 {
    static int cnt = 0;
    static Node root;

    static class Node{
        int idx;
        Node parent;
        ArrayList<Node> child;

        public Node(int idx) {
            this.idx = idx;
            this.child = new ArrayList<>();
            this.parent = null;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int rmIdx = Integer.parseInt(br.readLine());

        root = new Node(-1);
        Node[] nodes = new Node[N];

        for(int i = 0; i < N; i++){
            nodes[i] = new Node(i);
        }

        insertNode(arr, nodes);
        removeNode(nodes, rmIdx);
        findLeaf(root);
        System.out.println(cnt);

    }

    public static void findLeaf(Node cur){
        if(root.child.isEmpty()){
            return;
        }
        if(cur.child.size() == 0){
            cnt++;
            return;
        }

        for(Node child : cur.child){
            findLeaf(child);
        }
    }

    public static void insertNode(int[] arr, Node[] nodes){
        for(int i = 0; i < arr.length; i++){
            int p = arr[i];

            if(p == -1){
                root.child.add(nodes[i]);
                nodes[i].parent = root;
                continue;
            }

            for(int j = 0; j < nodes.length; j++){
                if(nodes[j].idx == p){
                    nodes[j].child.add(nodes[i]);
                    nodes[i].parent = nodes[j];
                    break;
                }
            }
        }
    }

    public static void removeNode(Node[] nodes, int idx){
        for(Node node : nodes){
            if(node.idx == idx){
                node.parent.child.remove(node);
                node.parent = null;
                break;
            }
        }
    }
}