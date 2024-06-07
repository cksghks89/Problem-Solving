import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] A, B;
    private static List<Integer>[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) A[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        B = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) B[i] = Integer.parseInt(st.nextToken());

        memo = new List[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                memo[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A[i] == B[j]) {
                    // 3개 비교
                    List<Integer> temp1 = getCurrentLast(memo[i - 1][j - 1], A[i]);
                    List<Integer> temp2;

                    if (compare(memo[i - 1][j], memo[i][j - 1]) < 0) temp2 = memo[i][j - 1];
                    else temp2 = memo[i - 1][j];

                    if (compare(temp1, temp2) < 0) memo[i][j] = temp2;
                    else memo[i][j] = temp1;
                } else {
                    // 2개 비교
                    if (compare(memo[i - 1][j], memo[i][j - 1]) < 0) memo[i][j] = memo[i][j - 1];
                    else memo[i][j] = memo[i - 1][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(memo[N][M].size()).append('\n');
        for (int i = 0; i < memo[N][M].size(); i++) {
            sb.append(memo[N][M].get(i)).append(' ');
        }

        System.out.println(sb);
    }

    private static List<Integer> getCurrentLast(List<Integer> prev, int cur) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < prev.size(); i++) {
            if (prev.get(i) < cur) {
                result.add(cur);
                return result;
            } else {
                result.add(prev.get(i));
            }
        }
        result.add(cur);
        return result;
    }

    private static int compare(List<Integer> l1, List<Integer> l2) {
        for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {
            if (l1.get(i) < l2.get(i)) return -1;
            else if (l1.get(i) > l2.get(i)) return 1;
        }
        return l1.size() - l2.size();
    }
}
