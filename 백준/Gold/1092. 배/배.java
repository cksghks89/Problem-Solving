import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class Main {
    private static int N, M;
    private static int[] crane;
    private static int[] box;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        crane = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        box = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        crane = reverse(crane);
        box = reverse(box);

        if (box[0] > crane[0]) {
            System.out.println(-1);
            return;
        }

        Queue<int[]>[] queueList = new ArrayDeque[N];
        for (int i = 0; i < N; i++) {
            queueList[i] = new ArrayDeque<>();
            for (int j = 0; j < M; j++) {
                if (crane[i] >= box[j]) {
                    queueList[i].offer(new int[]{j, box[j]});
                }
            }
        }

        boolean[] visited = new boolean[M];

        int time = 0;
        int cnt = 0;
        while (cnt != M) {
            time += 1;
            for (int i = 0; i < N; i++) {
                while (!queueList[i].isEmpty()) {
                    int[] cur = queueList[i].poll();
                    if (visited[cur[0]]) continue;
                    visited[cur[0]] = true;
                    cnt += 1;
                    break;
                }
            }
        }

        System.out.println(time);
    }

    private static int[] reverse(int[] arr) {
        Arrays.sort(arr);
        int[] reverse = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reverse[i] = arr[arr.length - 1 - i];
        }
        return reverse;
    }
}
