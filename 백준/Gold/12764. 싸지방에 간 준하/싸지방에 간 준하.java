import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        PriorityQueue<Integer> computer = new PriorityQueue<>((o1, o2) -> o1 - o2);

        int id = 0;
        int[] cnt = new int[100_001];
        for (int i = 0; i < N; i++) {
            int[] cur = arr[i];

            if (computer.isEmpty() && (pq.isEmpty() || pq.peek()[1] > cur[0])) {
                id += 1;
                pq.offer(new int[]{id, cur[1]});
                cnt[id] += 1;
            } else {
                while (!pq.isEmpty() && pq.peek()[1] < cur[0]) {
                    computer.offer(pq.poll()[0]);
                }
                int computerId = computer.poll();
                cnt[computerId] += 1;
                pq.offer(new int[]{computerId, cur[1]});
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(id).append('\n');
        for (int i = 1; i <= id; i++) {
            sb.append(cnt[i]).append(' ');
        }
        System.out.println(sb);
    }
}
