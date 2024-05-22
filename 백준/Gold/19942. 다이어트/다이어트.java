import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] minimumNutrient;
    private static int[][] nutrients;

    private static List<Integer> answer = new ArrayList<>();
    private static int cost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        minimumNutrient = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        nutrients = new int[N][];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nutrients[i] = new int[]{p, f, s, v, c};
        }

        // 3 <= N <= 15 : DP vs 부분집합 -> 부분집합 모든 경우의 수 : 2^15 = 32000 (가능)
        subSet(0, new int[4], 0, new ArrayList<>());

        if (cost == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cost).append('\n');
        answer.forEach(o -> sb.append(o).append(' '));
        System.out.println(sb);
    }

    private static void subSet(int cnt, int[] curNutrient, int curCost, List<Integer> include) {
        if (cnt == N) {
            if (!isSatisfied(curNutrient)) return;
            if (curCost < cost || (curCost == cost && isOrderFirst(include, answer))) {
                cost = curCost;
                answer.clear();
                answer.addAll(include);
            }
            return;
        }

        // 포함
        for (int i = 0; i < 4; i++) curNutrient[i] += nutrients[cnt][i];
        include.add(cnt + 1);
        subSet(cnt + 1, curNutrient, curCost + nutrients[cnt][4], include);
        include.remove(include.size() - 1);
        for (int i = 0; i < 4; i++) curNutrient[i] -= nutrients[cnt][i];

        // 미포함
        subSet(cnt + 1, curNutrient, curCost, include);
    }

    private static boolean isSatisfied(int[] curNutrient) {
        for (int i = 0; i < 4; i++) {
            if (curNutrient[i] < minimumNutrient[i]) return false;
        }
        return true;
    }

    private static boolean isOrderFirst(List<Integer> o1, List<Integer> o2) {
        int len = Math.min(o1.size(), o2.size());
        for (int i = 0; i < len; i++) {
            if (o1.get(i) == o2.get(i)) continue;
            if (o1.get(i) < o2.get(i)) return true;
            else if (o1.get(i) > o2.get(i)) return false;
        }
        return o1.size() < o2.size();
    }
}
