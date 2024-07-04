import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] alphabetBitMask;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init(br);

        int rememberBitMask = 0;
        for (int i = 0; i <= 'z' - 'a'; i++) {
            rememberBitMask |= 1 << i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            char x = st.nextToken().charAt(0);

            if (o == 1) {
                rememberBitMask = rememberBitMask ^ (1 << (x - 'a'));
            } else if (o == 2) {
                rememberBitMask = rememberBitMask | (1 << (x - 'a'));
            }

            int count = 0;
            for (int j = 0; j < N; j++) {
                if ((rememberBitMask & alphabetBitMask[j]) == alphabetBitMask[j]) {
                    count += 1;
                }
            }

            sb.append(count).append('\n');
        }

        System.out.println(sb);
    }

    private static void init(BufferedReader br) throws IOException {
        alphabetBitMask = new int[N];

        for (int i = 0; i < N; i++) {
            String cur = br.readLine();
            for (int j = 0; j < cur.length(); j++) {
                int bit = 1 << (cur.charAt(j) - 'a');
                alphabetBitMask[i] |= bit;
            }
        }
    }
}
