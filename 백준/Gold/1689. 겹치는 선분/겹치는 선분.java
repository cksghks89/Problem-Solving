import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        line = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(line, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (pq.isEmpty()) {
                pq.offer(line[i][1]);
            } else {
                while (!pq.isEmpty() && pq.peek() <= line[i][0]) {
                    pq.poll();
                }
                pq.offer(line[i][1]);
            }
            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);
    }
}
