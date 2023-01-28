/*
    Boj 1766. 문제집
    level. gold 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1766_문제집 {
    static class Node {
        int id;
        Set<Node> pre;
        Set<Node> next;

        public Node(int id) {
            this.id = id;
            pre = new HashSet<>();
            next = new HashSet<>();
        }
    }

    static int N, M;
    static Node[] nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodeList = new Node[N + 1];

        for (int i = 1; i <= N; i++) {
            nodeList[i] = new Node(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            nodeList[A].next.add(nodeList[B]);
            nodeList[B].pre.add(nodeList[A]);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer n : getSequence()) {
            sb.append(n).append(" ");
        }

        System.out.println(sb.toString());
    }

    static ArrayList<Integer> getSequence() {
        ArrayList<Integer> sequence = new ArrayList<>();

        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> x.id - y.id);

        for (int i = 1; i <= N; i++) {
            if (nodeList[i].pre.isEmpty()) {
                pq.add(nodeList[i]);
            }
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            sequence.add(cur.id);

            for (Node node : cur.next) {
                node.pre.remove(cur);

                if (node.pre.isEmpty()) {
                    pq.add(node);
                }
            }
        }

        return sequence;
    }
}
