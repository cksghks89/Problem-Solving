import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] arr;

    private static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        set = new HashSet<>();

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        List<Integer> answer = new ArrayList<>(set);
        answer.sort((o1, o2) -> o1 - o2);

        StringBuilder sb = new StringBuilder();
        sb.append(set.size()).append('\n');
        for (int cur : answer) sb.append(cur).append('\n');

        System.out.println(sb);
    }

    private static void bfs(int idx) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(idx);

        Set<Integer> upper = new HashSet<>();
        Set<Integer> lower = new HashSet<>();

        while (!queue.isEmpty()) {
            int curUpper = queue.poll();
            int curLow = arr[curUpper];

            upper.add(curUpper);
            if (upper.contains(curLow)) {
                lower.add(curLow);
                break;
            }

            queue.offer(curLow);
            lower.add(curLow);
        }

        if (upper.equals(lower)) {
            set.addAll(upper);
        }
    }
}
