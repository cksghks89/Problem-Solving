import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        line = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            line[i] = new int[]{S, E, C};
        }

        Arrays.sort(line, (o1, o2) -> o1[0] - o2[0]);

        int answer = 0;
        int K = 0;
        List<int[]> list = new ArrayList<>();

        int s = line[0][0];
        int e = line[0][1];
        int c = line[0][2];
        for (int i = 1; i < N; i++) {
            if (line[i][0] <= e) {
                e = Math.max(e, line[i][1]);
                c = Math.min(c, line[i][2]);
            } else {
                list.add(new int[]{s, e, c});
                s = line[i][0];
                e = line[i][1];
                c = line[i][2];
            }
        }
        list.add(new int[]{s, e, c});

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append('\n');
        for (int[] cur : list) {
            sb.append(cur[0] + " " + cur[1] + " " + cur[2]).append('\n');
        }

        System.out.println(sb);
    }
}
