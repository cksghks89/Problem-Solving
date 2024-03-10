import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());

        long p1 = 1;
        long p2 = 2;

        StringBuilder sb = new StringBuilder();
        while (p1 < p2) {
            if (p2 * p2 - p1 * p1 < G) {
                p2 += 1;
            } else if (p2 * p2 - p1 * p1 > G) {
                p1 += 1;
            } else {
                sb.append(p2).append('\n');
                p1 += 1;
                p2 += 1;
            }
        }

        if (sb.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
    }
}
