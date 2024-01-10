import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] lecture;

    public static void main(String[] args) throws IOException {
        // 끝나는 시간에 따른 그리디
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        lecture = new int[N][2];

        for (int i = 0; i < N; i++) {
            lecture[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Arrays.sort(lecture, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int[] cur = lecture[i];
            if (!pq.isEmpty() && pq.peek() <= cur[0]) {
                pq.poll();
            }
            pq.offer(cur[1]);
        }

        System.out.println(pq.size());
    }
}
