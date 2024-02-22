import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int A;
    static int B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            System.out.println(bfs().toString());
        }
    }

    static String bfs() {
        Queue<Integer> queue = new LinkedList<>();
        Queue<String> op = new LinkedList<>();
        boolean[] visited = new boolean[10000];

        queue.offer(A);
        op.offer("");
        visited[A] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            String str = op.poll();


            if (cur == B) {
                return str;
            }

            // D
            int d = (cur * 2) % 10000;
            if (!visited[d]) {
                visited[d] = true;
                queue.offer(d);
                op.offer(str + "D");
            }

            // S
            int s = (cur - 1 + 10000) % 10000;
            if (!visited[s]) {
                visited[s] = true;
                queue.offer(s);
                op.offer(str + "S");
            }

            // L
            int l = lOperation(cur);
            if (!visited[l]) {
                visited[l] = true;
                queue.offer(l);
                op.offer(str+"L");
            }

            int r = rOperation(cur);
            if (!visited[r]) {
                visited[r] = true;
                queue.offer(r);
                op.offer(str+"R");
            }
        }
        return "";
    }

    static int lOperation(int l) {
        int d4 = l % 10;
        int d3 = (l / 10) % 10;
        int d2 = (l / 100) % 10;
        int d1 = (l / 1000);

        return d2 * 1000 + d3 * 100 + d4 * 10 + d1;
    }

    static int rOperation(int r) {
        int d4 = r % 10;
        int d3 = (r / 10) % 10;
        int d2 = (r / 100) % 10;
        int d1 = r / 1000;

        return d4 * 1000 + d1 * 100 + d2 * 10 + d3;
    }
}
