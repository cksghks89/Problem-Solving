/*
    Boj 12852. 1로 만들기 2
    level. silver 1
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Boj_12852_1로만들기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Node ans = bfs(N);

        System.out.println(ans.cnt);
        ans.sequence.forEach(x -> System.out.print(x + " "));
    }

    static Node bfs(int N) {
        Queue<Node> queue = new LinkedList<>();

        Node start = new Node(N, 0);
        start.sequence.add(N);
        queue.offer(start);

        boolean[] visited = new boolean[N + 1];

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.idx == 1) {
                return cur;
            }

            if(cur.idx == 0){
                continue;
            }

            if (cur.idx % 3 == 0 && !visited[cur.idx / 3]) {
                visited[cur.idx / 3] = true;
                Node next = new Node(cur.idx / 3, cur.cnt + 1);
                next.sequence.addAll(cur.sequence);
                next.sequence.add(cur.idx / 3);
                queue.offer(next);
            }

            if (cur.idx % 2 == 0 && !visited[cur.idx / 2]) {
                visited[cur.idx / 2] = true;
                Node next = new Node(cur.idx / 2, cur.cnt + 1);
                next.sequence.addAll(cur.sequence);
                next.sequence.add(cur.idx / 2);
                queue.offer(next);
            }

            if (!visited[cur.idx - 1]) {
                visited[cur.idx - 1] = true;
                Node next = new Node(cur.idx - 1, cur.cnt + 1);
                next.sequence.addAll(cur.sequence);
                next.sequence.add(cur.idx - 1);
                queue.offer(next);
            }
        }
        return null;
    }

    static class Node {
        int idx;
        int cnt;
        ArrayList<Integer> sequence;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
            sequence = new ArrayList<>();
        }
    }
}
