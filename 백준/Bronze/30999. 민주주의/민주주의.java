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

        int answer = 0;
        for (int tc = 0; tc < N; tc++) {
            String line = br.readLine();
            int cnt = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'O') cnt += 1;
            }
            if (cnt >= line.length() / 2 + 1) answer += 1;
        }

        System.out.println(answer);
    }
}
