import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, L;
    private static int[][] fields;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        fields = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int[] row = getLine(i, true);
            int[] col = getLine(i, false);

            if (isPassTheLine(row)) cnt += 1;
            if (isPassTheLine(col)) cnt += 1;
        }

        System.out.println(cnt);
    }

    private static int[] getLine(int idx, boolean isRow) {
        int[] line = new int[N];

        for (int i = 0; i < N; i++) {
            if (isRow) {
                line[i] = fields[idx][i];
            } else {
                line[i] = fields[i][idx];
            }
        }

        return line;
    }

    private static boolean isPassTheLine(int[] line) {
        boolean[] putLine = new boolean[N];

        for (int i = 1; i < N; i++) {
            if (Math.abs(line[i - 1] - line[i]) >= 2) return false;
            if (line[i - 1] == line[i]) continue;

            if (line[i - 1] < line[i]) {
                if (!isSameRange(line, i - L, i, putLine)) return false;
                for (int j = i - L; j < i; j++) putLine[j] = true;
            } else if (line[i - 1] > line[i]) {
                if (!isSameRange(line, i, i + L, putLine)) return false;
                for (int j = i; j < i + L; j++) putLine[j] = true;
            }
        }

        return true;
    }

    private static boolean isSameRange(int[] line, int l, int r, boolean[] putLine) {
        if (r > line.length || l < 0) return false;
        int value = line[l];
        for (int i = l; i < r; i++) {
            if (line[i] != value || putLine[i]) return false;
        }
        return true;
    }
}
