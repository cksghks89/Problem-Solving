import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int year = E;
        int s = E;
        int m = E;

        while (s != S || m != M) {
            year += 15;
            s = (s + 15) > 28 ? s + 15 - 28 : s + 15;
            m = (m + 15) > 19 ? m + 15 - 19 : m + 15;
        }

        System.out.println(year);
    }
}
