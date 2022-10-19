/*
    Boj 15650. N과 M (2)
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_15650_N과M2 {
    static boolean[] visited;
    static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        list = new ArrayList<>();

        combination(0, N, M, M);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);

            for(int t : cur){
                sb.append(t).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static void combination(int depth, int n, int r, int M) {
        if (r == 0) {
            int[] cur = new int[M];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    cur[cnt++] = i + 1;
                }
            }
            list.add(cur);
            return;
        }
        if (depth == n) {
            return;
        }

        visited[depth] = true;
        combination(depth + 1, n, r - 1, M);
        visited[depth] = false;
        combination(depth + 1, n, r, M);
    }
}
