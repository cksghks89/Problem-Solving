/* 
    Boj 17135. 캐슬 디펜스
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17135_캐슬디펜스 {
    static int N, M, D;
    static int[][] map;
    static int count;
    static int maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        count = 0;
        maxCount = Integer.MIN_VALUE;

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        comb(0, 0, new int[3]);
        System.out.println(maxCount);
    }

    static void comb(int cnt, int start, int[] out) {
        if (cnt == 3) {
            int[][] curMap = copyMap();
            count = 0;
            for (int i = 0; i < N; i++) defense(out, curMap);
            maxCount = Math.max(maxCount, count);
            return;
        }

        for (int i = start; i < M; i++) {
            out[cnt] = i;
            comb(cnt + 1, i + 1, out);
        }
    }

    static void defense(int[] arrow, int[][] map) {
        // 모든 적 큐잉
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 각 궁수별 제거할 적 탐색
        int[][] remove = new int[3][3];
        for (int i = 0; i < 3; i++) Arrays.fill(remove[i], Integer.MAX_VALUE);
        while (!queue.isEmpty()) {
            int[] enemy = queue.poll();
            for (int i = 0; i < 3; i++) {
                int dist = Math.abs(enemy[0] - N) + Math.abs(enemy[1] - arrow[i]);
                if (dist <= D && remove[i][2] > dist) {
                    remove[i][0] = enemy[0];
                    remove[i][1] = enemy[1];
                    remove[i][2] = dist;
                } else if (remove[i][2] == dist) {
                    remove[i][0] = enemy[1] < remove[i][1] ? enemy[0] : remove[i][0];
                    remove[i][1] = Math.min(enemy[1], remove[i][1]);
                }
            }
        }

        // 적 제거
        for (int i = 0; i < 3; i++) {
            int[] cur = remove[i];
            if (cur[2] == Integer.MAX_VALUE) continue;
            count += map[cur[0]][cur[1]];
            map[cur[0]][cur[1]] = 0;
        }

        // 아래로 한 칸 밀기
        for (int i = N - 1; i > 0; i--) {
            map[i] = map[i - 1];
        }
        map[0] = new int[M];
    }

    private static int[][] copyMap() {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
}
