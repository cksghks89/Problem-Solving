/*
    Boj 15649. N과 M (1)
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_15649_N과M1 {
    static boolean[] visited;
    static ArrayList<int[]> list;
    static int[] out;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        out = new int[M];
        visited = new boolean[N];

        perm(0, N, M, out);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++){
            int[] cur = list.get(i);

            for(int t : cur){
                sb.append(t + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void perm(int depth, int n, int r, int[] out){
        if(depth == r){
            list.add(out.clone());
            return;
        }

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                visited[i] = true;
                out[depth] = i+1;
                perm(depth+1, n, r, out);
                visited[i] = false;
            }
        }
    }
}
