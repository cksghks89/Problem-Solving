import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        long Z = Y * 100 / X;

        long p1 = 1;
        long p2 = Integer.MAX_VALUE;

        long min = Integer.MAX_VALUE;
        while (p1 <= p2) {
            long mid = (p1 + p2) / 2;

            long cur = ((Y + mid) * 100) / (X + mid);
            if (cur != Z) {
                min = mid;
                p2 = mid - 1;
            } else {
                p1 = mid + 1;
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
}
