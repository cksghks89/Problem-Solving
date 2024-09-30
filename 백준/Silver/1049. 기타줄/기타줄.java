import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int six = Integer.MAX_VALUE;
        int one = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            six = Math.min(six, Integer.parseInt(st.nextToken()));
            one = Math.min(one, Integer.parseInt(st.nextToken()));
        }

        if (one * 6 < six) {
            System.out.println(one * N);
        } else {
            int cost1 = (N / 6 + 1) * six;
            int cost2 = (N / 6) * six + (N % 6) * one;
            System.out.println(Math.min(cost1, cost2));
        }
    }
}
