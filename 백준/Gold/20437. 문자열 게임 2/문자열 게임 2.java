import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int K;
    private static List<Integer>[] index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            index = new List['z' + 1];
            for (int i = 'a'; i <= 'z'; i++) {
                index[i] = new ArrayList<>();
            }

            char[] input = br.readLine().toCharArray();
            K = Integer.parseInt(br.readLine());

            for (int i = 0; i < input.length; i++) {
                index[input[i]].add(i);
            }

            int shortLen = Integer.MAX_VALUE;
            int longLen = -1;
            for (int i = 'a'; i <= 'z'; i++) {
                if (index[i].size() < K) continue;

                for (int j = 0; j <= index[i].size() - K; j++) {
                    shortLen = Math.min(index[i].get(j + K - 1) - index[i].get(j) + 1, shortLen);
                    longLen = Math.max(index[i].get(j + K - 1) - index[i].get(j) + 1, longLen);
                }
            }

            if (longLen == -1) {
                sb.append(-1).append('\n');
            } else {
                sb.append(shortLen).append(" ").append(longLen).append('\n');
            }
        }

        System.out.println(sb);
    }
}
