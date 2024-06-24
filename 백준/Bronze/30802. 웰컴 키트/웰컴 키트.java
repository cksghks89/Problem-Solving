import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] size;
    private static int T, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        size = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int shirt = 0;
        for (int i = 0; i < size.length; i++) {
            if (size[i] == 0) continue;
            shirt += (size[i] - 1) / T + 1;
        }

        int set = N / P;
        int pen = N - P * set;

        System.out.println(shirt);
        System.out.println(set + " " + pen);
    }
}
