import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int k, n;
    private static int[] seq;

    private static int[][] ladder;
    private static int qLine;

    private static int[] out;

    private static StringBuilder answer;

    private static int[] start;
    private static int[] end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // k <= 26, 모든 조합을 돌리면? -> 2^26 보다 작음 -> 3200만 보다 적은 경우의 수
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        seq = Arrays.stream(br.readLine().split("")).mapToInt(o1 -> o1.charAt(0) - 'A').toArray();

        ladder = new int[n][k - 1];

        out = new int[k - 1];
        answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < k - 1; j++) {
                if (line.charAt(j) == '*' || line.charAt(j) == '?') {
                    if (line.charAt(j) == '?') qLine = i;
                    ladder[i][j] = 0;
                } else if (line.charAt(j) == '-') {
                    ladder[i][j] = 1;
                }
            }
        }

        // qLine 전까지 미리 사다리 타놓기
        start = executeLadder(0, qLine);

        end = executeLadder(qLine + 1, n);

        // qLine 이후부터 끝까지 미리 사다리 타놓기

        subset(0);

        if (answer.length() == 0) {
            for (int i = 0; i < k - 1; i++) answer.append('x');
        }
        System.out.println(answer);
    }

    private static void subset(int cnt) {
        if (answer.length() != 0) return;

        if (cnt == k - 1) {
            int[] next = new int[k];
            for (int i = 0; i < k; i++) {
                int idx = i;
                if (idx != k - 1 && out[idx] == 1) {
                    idx += 1;
                } else if (idx - 1 >= 0 && out[idx - 1] == 1) {
                    idx -= 1;
                }
                next[idx] = start[i];
            }

            int[] last = new int[k];
            for (int i = 0; i < k; i++) {
                last[i] = next[end[i]];
            }

            if (Arrays.equals(last, seq)) {
                for (int i = 0; i < k - 1; i++) {
                    if (out[i] == 0) answer.append('*');
                    else answer.append('-');
                }
            }
            return;
        }

        subset(cnt + 1);

        if (cnt == 0 || (cnt >= 1 && out[cnt - 1] == 0)) {
            out[cnt] = 1;
            subset(cnt + 1);
            out[cnt] = 0;
        }

    }

    private static int[] executeLadder(int s, int e) {
        int[] init = new int[k];
        for (int i = 0; i < k; i++) {
            init[i] = i;
        }

        // 사다리 타기
        int[] end = new int[k];
        for (int i = 0; i < k; i++) {
            int result = i;
            for (int j = s; j < e; j++) {
                if (result != k - 1 && ladder[j][result] == 1) {
                    result += 1;
                } else if (result - 1 >= 0 && ladder[j][result - 1] == 1) {
                    result -= 1;
                }
            }

            end[result] = i;
        }

        return end;
    }
}
