import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] power;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        power = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                power[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            comb(0, 0, i, new HashSet<>());
        }

        System.out.println(answer);
    }

    private static void comb(int start, int cnt, int end, HashSet<Integer> set) {
        if (cnt == end) {
            int d = calc(set);
            answer = Math.min(answer, d);
            return;
        }

        for (int i = start; i < N; i++) {
            set.add(i);
            comb(i + 1, cnt + 1, end, set);
            set.remove(i);
        }
    }

    private static int calc(HashSet<Integer> set) {
        int startTeam = 0;
        int linkTeam = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (set.contains(i) && set.contains(j)) {
                    startTeam += power[i][j];
                } else if (!set.contains(i) && !set.contains(j)) {
                    linkTeam += power[i][j];
                }
            }
        }

        return Math.abs(startTeam - linkTeam);
    }
}
