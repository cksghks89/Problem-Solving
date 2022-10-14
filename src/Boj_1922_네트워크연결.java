/*
    Boj 1922. 네트워크 연결
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_1922_네트워크연결 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parents = new int[N+1];
        for(int i = 0; i < N+1; i++){
            parents[i] = i;
        }

        ArrayList<int[]> edges = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.add(new int[]{s, e, w});
        }

        edges.sort((x, y) -> x[2] - y[2]);

        int weight = 0;
        for(int i = 0; i < M; i++){
            int[] cur = edges.get(i);

            if(find(cur[0]) != find(cur[1])){
                union(cur[0], cur[1]);
                weight += cur[2];
            }
        }
        System.out.println(weight);
    }
    static void union(int a, int b){
        if(find(a) != find(b)){
            parents[find(a)] = find(b);
        }
    }

    static int find(int a){
        if(a == parents[a]){
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}
