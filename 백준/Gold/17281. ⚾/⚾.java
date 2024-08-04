import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int inning;
    private static int[][] record;
    private static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        inning = Integer.parseInt(br.readLine());
        record = new int[inning][9];

        for (int i = 0; i < inning; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                record[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        perm(0, 0, new int[9]);

        System.out.println(score);
    }

    private static void perm(int cnt, int visited, int[] out) {
        if (cnt == 3) {
            out[cnt] = 0;
            perm(cnt + 1, visited, out);
            return;
        }
        if (cnt == 9) {
            int currentScore = doGame(out);
            score = Math.max(score, currentScore);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if ((visited & (1 << i)) > 0) continue;
            out[cnt] = i;
            perm(cnt + 1, visited | (1 << i), out);
        }
    }

    private static int doGame(int[] seq) {
        int currentScore = 0;
        int order = 0;

        for (int i = 0; i < inning; i++) {
            boolean first = false;
            boolean second = false;
            boolean third = false;
            int outCount = 0;

            while (outCount != 3) {
                if (record[i][seq[order]] == 0) {
                    outCount += 1;
                } else if (record[i][seq[order]] == 1) {
                    if (third) currentScore += 1;
                    third = second;
                    second = first;
                    first = true;
                } else if (record[i][seq[order]] == 2) {
                    if (third) currentScore += 1;
                    if (second) currentScore += 1;
                    third = first;
                    second = true;
                    first = false;
                } else if (record[i][seq[order]] == 3) {
                    if (third) currentScore += 1;
                    if (second) currentScore += 1;
                    if (first) currentScore += 1;
                    third = true;
                    second = false;
                    first = false;
                } else if (record[i][seq[order]] == 4) {
                    if (third) currentScore += 1;
                    if (second) currentScore += 1;
                    if (first) currentScore += 1;
                    currentScore += 1;
                    first = false;
                    second = false;
                    third = false;
                }
                order = (order + 1) % 9;
            }
        }

        return currentScore;
    }
}
