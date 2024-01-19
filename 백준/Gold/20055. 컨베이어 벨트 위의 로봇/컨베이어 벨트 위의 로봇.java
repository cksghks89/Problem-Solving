import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[] A;
    private static Queue<Integer> robotsQueue;
    private static boolean[] robotsPos;
    private static int zeroCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        robotsQueue = new ArrayDeque<>();
        robotsPos = new boolean[2 * N];
        zeroCount = 0;

        for (int i = 0; i < 2 * N; i++) {
            if (A[i] == 0) zeroCount += 1;
        }

        // N 칸에 로봇이 도착하면 그 즉시 내림

        int step = 0;
        while (true) {
            step += 1;

            rotate();
            robotsMove();
            dropRobots();
            if (isEnd()) break;
        }

        System.out.println(step);
    }

    private static boolean isEnd() {
        return zeroCount >= K;
    }

    private static void dropRobots() {
        if (A[0] != 0) {
            A[0] -= 1;
            robotsQueue.offer(0);
            if (A[0] == 0) {
                zeroCount += 1;
            }
        }
    }

    private static void robotsMove() {
        int qSize = robotsQueue.size();
        for (int i = 0; i < qSize; i++) {
            int cur = robotsQueue.poll();
            int next = (cur + 1) % (2 * N);

            if (A[next] != 0 && !robotsPos[next]) {
                robotsPos[cur] = false;
                A[next] -= 1;

                if (A[next] == 0) zeroCount += 1;

                if (next != N - 1) {
                    robotsQueue.offer(next);
                    robotsPos[next] = true;
                }
            } else {
                robotsQueue.offer(cur);
            }
        }
    }

    private static void rotate() {
        int temp = A[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            A[i] = A[i - 1];
        }
        A[0] = temp;

        int qSize = robotsQueue.size();
        for (int i = 0; i < qSize; i++) {
            int cur = robotsQueue.poll();
            robotsPos[cur] = false;
            cur = (cur + 1) % (2 * N);

            if (cur != N - 1) {
                robotsQueue.offer(cur);
                robotsPos[cur] = true;
            }
        }
    }
}
