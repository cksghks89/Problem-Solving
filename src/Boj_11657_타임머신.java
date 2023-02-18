/*
    Boj 11657. 타임머신
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11657_타임머신 {
    static int N, M;
    static ArrayList<int[]> edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edge = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edge.add(new int[]{A, B, C});
        }

        if (N == 1) return;


        long[] result = bellmanFord();
        if (result == null) {
            System.out.println(-1);
            return;
        }

        for (int i = 2; i <= N; i++) {
            if (result[i] == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(result[i]);
            }
        }

    }

    static long[] bellmanFord() {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < edge.size(); j++) {
                int s = edge.get(j)[0];
                int e = edge.get(j)[1];
                int w = edge.get(j)[2];

                if (dist[s] == Integer.MAX_VALUE) {
                    continue;
                }
                if (dist[e] > dist[s] + w) {
                    dist[e] = dist[s] + w;
                    if (i == N - 1) {
                        return null;
                    }
                }
            }
        }
        return dist;
    }
}
