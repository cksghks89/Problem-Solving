package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SWEA_1249_보급로 {
    static int N;
    static int[][] map;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            sb.append('#').append(tc).append(' ').append(priorityBfs()).append('\n');
        }
        System.out.print(sb.toString());
    }

    static int priorityBfs() {
        int[][] costMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(costMap[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(101, (x, y) -> x[2] - y[2]);
        queue.offer(new int[]{0, 0, 0});
        costMap[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == N - 1 && cur[1] == N - 1) {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) continue;
                if (cur[2] + map[nx][ny] < costMap[nx][ny]) {
                    costMap[nx][ny] = cur[2] + map[nx][ny];
                    queue.offer(new int[]{nx, ny, costMap[nx][ny]});
                }
            }
        }

        return -1;
    }
}
