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
                char[] line = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line[j] - '0';
                }
            }

            sb.append("#"+tc+" ").append(priorityBfs()).append("\n");
        }
        System.out.print(sb.toString());
    }

    static int priorityBfs() {
        int[][] costMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(costMap[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        queue.offer(new int[]{0, 0, 0});
        costMap[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];

            if (x == N - 1 && y == N - 1) {
                return cost;
            }

            if (costMap[x][y] != cost) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isInRange(nx, ny) && cost + map[nx][ny] < costMap[nx][ny]) {
                    costMap[nx][ny] = cost + map[nx][ny];
                    queue.offer(new int[]{nx, ny, costMap[nx][ny]});
                }
            }
        }

        return -1;
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }
}
