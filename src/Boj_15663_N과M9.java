/*
    Boj 15663. N과 M(9)
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj_15663_N과M9 {
    static int[] arr;
    static boolean[] visited;
    static Set<String> set;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        visited = new boolean[N];
        int[] out = new int[M];
        set = new HashSet<>();
        sb = new StringBuilder();

        Arrays.sort(arr);

        combination(N, M, 0, out);
        System.out.print(sb.toString());
    }

    static void combination(int n, int m, int depth, int[] out) {
        if (depth == m) {
            StringBuilder cur = new StringBuilder();
            for (int num : out) {
                cur.append(num).append(" ");
            }

            if (!set.contains(cur.toString())) {
                set.add(cur.toString());
                sb.append(cur).append("\n");
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                combination(n, m, depth + 1, out);
                visited[i] = false;
            }
        }
    }
}
