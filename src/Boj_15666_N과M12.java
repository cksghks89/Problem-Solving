/*
    Boj 15666. N과 M (12)
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_15666_N과M12 {
    static int[] arr;
    static StringBuilder sb;
    static Set<ArrayList<Integer>> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        sb = new StringBuilder();
        set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        combWithRepetition(N, M, 0, 0, new ArrayList<>());

        System.out.print(sb.toString());
    }

    static void combWithRepetition(int n, int r, int depth, int start, ArrayList<Integer> out) {
        if (depth == r) {
            if (set.contains(out)) {
                return;
            }
            set.add(out);

            for (int num : out) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {
            out.add(arr[i]);
            combWithRepetition(n, r, depth + 1, i, out);
            out.remove(out.size() - 1);
        }
    }
}
