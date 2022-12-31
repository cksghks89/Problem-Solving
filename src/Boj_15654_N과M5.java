/*
    Boj 15654. N과 M(5)
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_15654_N과M5 {
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] out = new int[M];

        visited = new boolean[N];
        sb = new StringBuilder();

        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(arr);

        permutation(N, M, 0, out);

        System.out.print(sb.toString());
    }

    static void permutation(int n, int m, int depth, int[] out) {
        if (depth == m) {
            for(int num : out){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                permutation(n, m, depth + 1, out);
                visited[i] = false;
            }
        }
    }
}
