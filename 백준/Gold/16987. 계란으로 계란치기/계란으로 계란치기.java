import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] eggs;
    private static int answer;

    public static void main(String[] args) throws IOException {
        // 최대 8개

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];
        answer = -1;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        subset(0);

        System.out.println(answer);
    }

    private static void subset(int idx) {
        if (idx == N) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (eggs[i][0] <= 0) cnt += 1;
            }
            answer = Math.max(answer, cnt);
            return;
        }

        if (eggs[idx][0] <= 0) {
            subset(idx + 1);
            return;
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (i == idx) continue;
            if (eggs[i][0] <= 0) continue;

            cnt += 1;
            eggs[i][0] -= eggs[idx][1];
            eggs[idx][0] -= eggs[i][1];
            subset(idx + 1);
            eggs[i][0] += eggs[idx][1];
            eggs[idx][0] += eggs[i][1];
        }

        if (cnt == 0) {
            subset(idx + 1);
        }
    }
}
