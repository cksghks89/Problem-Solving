import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Long> decrease;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        decrease = new ArrayList<>();
        decrease.add(0L);

        for (int i = 1; i < 11; i++) {
            calc(0, 10, i, 0);
        }

        if (decrease.size() > N) {
            System.out.println(decrease.get(N));
        } else {
            System.out.println(-1);
        }
    }

    private static void calc(int cnt, int last, int end, long num) {
        if (cnt == end) {
            decrease.add(num);
            return;
        }

        for (int i = 0; i < last; i++) {
            if (cnt == 0 && i == 0) continue;

            long next = num * 10 + i;
            calc(cnt + 1, i, end, next);
        }
    }
}
