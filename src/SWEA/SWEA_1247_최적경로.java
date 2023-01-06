package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_최적경로 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistance(Point p) {
            return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
        }
    }

    static int N;
    static Point[] points;
    static Point company;
    static int[][] graph;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            points = new Point[N + 1];
            graph = new int[N + 1][N + 1];
            result = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());

            int idx = 0;
            for (int i = 0; i < N + 2; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if (i == 1) {
                    company = new Point(x, y);
                    continue;
                }
                points[idx++] = new Point(x, y);
            }
            setGraph();
            dfs(0, 1, 0);

            sb.append("#" + tc + " ").append(result).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int src, int mask, int cost) {
        if (mask + 1 == 1 << (N + 1)) {
            result = Math.min(result, cost + points[src].getDistance(company));
            return;
        }

        for (int i = 0; i < N + 1; i++) {
            if ((mask & 1 << i) == 0 && graph[src][i] != 0) {
                dfs(i, mask | 1 << i, cost + graph[src][i]);
            }
        }
    }

    static void setGraph() {
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                    continue;
                }

                graph[i][j] = points[i].getDistance(points[j]);
            }
        }
    }
}
