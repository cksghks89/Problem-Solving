import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        list = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            perm(0, 0, new int[i]);
        }

        String input = null;
        StringBuilder sb = new StringBuilder();
        while ((input = br.readLine()) != null) {
            int num = Integer.parseInt(input);

            int idx = Collections.binarySearch(list, num);

            if (idx >= 0) {
                if (idx + 1 < list.size()) sb.append(list.get(idx + 1)).append('\n');
                else sb.append(0).append('\n');
            } else {
                idx = -idx - 1;
                if (idx < list.size()) sb.append(list.get(idx)).append('\n');
                else sb.append(0).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static void perm(int cnt, int visited, int[] out) {
        if (cnt == out.length) {
            int cur = 0;
            for (int i = 0; i < out.length; i++) {
                cur *= 10;
                cur += out[i];
            }
            list.add(cur);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if ((visited & (1 << i)) > 0) continue;
            out[cnt] = i;
            perm(cnt + 1, visited | (1 << i), out);
        }
    }
}
