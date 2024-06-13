import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] tooth;
    private static Set<Integer> set;
    private static List<Integer> candidate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tooth = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        candidate = new ArrayList<>();
        set = new HashSet<>();
        set.add(tooth[0]);

        for (int i = 1; i < tooth.length; i++) {
            candidate.add(tooth[i] - tooth[0]);
            set.add(tooth[i]);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < candidate.size(); i++) {
            int cur = candidate.get(i);
            boolean check = true;

            for (int j = 0; j < N; j++) {
                if (!(set.contains(tooth[j] - cur) || set.contains(tooth[j] + cur))) {
                    check = false;
                    break;
                }
            }

            if (check) {
                answer.add(cur);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append('\n');
        for (int d : answer) {
            sb.append(d).append(' ');
        }
        System.out.println(sb);
    }
}
