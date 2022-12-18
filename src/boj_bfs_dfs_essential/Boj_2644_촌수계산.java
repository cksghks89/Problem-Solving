/*
    Boj 2644. 촌수 계산
    level. silver 2
    solved by 송찬환
 */
package boj_bfs_dfs_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2644_촌수계산 {
    static int n;

    static class Node {
        int id;
        Integer parent;
        ArrayList<Integer> child;
        int chon;

        public Node(int id) {
            this.id = id;
            this.chon = 0;
            this.child = new ArrayList<>();
        }
    }

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int desc = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        Node[] node = new Node[n + 1];

        for (int i = 1; i <= n; i++) {
            node[i] = new Node(i);
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            node[x].child.add(y);
            node[y].parent = x;
        }

        int ans = bfs(node, src, desc);
        System.out.println(ans);
    }

    static int bfs(Node[] node, int src, int desc) {
        if (src == desc) {
            return 0;
        }

        int rtn = -1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node[src]);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if(cur.id == desc){
                rtn = cur.chon;
                break;
            }

            visited[cur.id] = true;

            if (cur.parent != null && !visited[cur.parent]) {
                node[cur.parent].chon = cur.chon + 1;
                queue.offer(node[cur.parent]);
            }

            for (int i = 0; i < cur.child.size(); i++) {
                int child = cur.child.get(i);
                if(!visited[child]){
                    node[child].chon = cur.chon + 1;
                    queue.offer(node[child]);
                }
            }
        }

        return rtn;
    }
}
