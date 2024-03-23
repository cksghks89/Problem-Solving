import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] line = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            if (h > o) {
                int temp = h;
                h = o;
                o = temp;
            }

            line[i] = new int[]{h, o};
        }

        int d = Integer.parseInt(br.readLine());

        Arrays.sort(line, (o1, o2) -> o1[1] - o2[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int start = line[i][1] - d;
            pq.offer(line[i][0]);
            while (!pq.isEmpty() && pq.peek() < start) {
                pq.poll();
            }
            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);
    }
}
