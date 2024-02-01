import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Line {
        Point p1;
        Point p2;

        int minX;
        int maxX;
        int minY;
        int maxY;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;

            minX = Math.min(p1.x, p2.x);
            maxX = Math.max(p1.x, p2.x);
            minY = Math.min(p1.y, p2.y);
            maxY = Math.max(p1.y, p2.y);
        }
    }

    private static int N;
    private static Line[] lines;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lines = new Line[N];
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            lines[i] = new Line(new Point(x1, y1), new Point(x2, y2));
            parent[i] = i;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;

                if (isIntersect(lines[i], lines[j])) {
                    union(i, j);
                }
            }
        }

        int[] group = new int[N];
        int maxCnt = 0;
        int groupCnt = 0;
        for (int i = 0; i < N; i++) {
            int cur = find(i);

            if (group[cur] == 0) groupCnt += 1;
            group[cur] += 1;

            maxCnt = Math.max(maxCnt, group[cur]);
        }

        System.out.println(groupCnt);
        System.out.println(maxCnt);
    }

    private static boolean isIntersect(Line l1, Line l2) {
        int ccw1 = ccw(l1.p1, l1.p2, l2.p1) * ccw(l1.p1, l1.p2, l2.p2);
        int ccw2 = ccw(l2.p1, l2.p2, l1.p1) * ccw(l2.p1, l2.p2, l1.p2);

        if (ccw1 == 0 && ccw2 == 0) {
            // 평행직선인 경우
            return l1.minX <= l2.maxX && l2.minX <= l1.maxX && l1.minY <= l2.maxY && l2.minY <= l1.maxY;
        }

        return ccw1 <= 0 && ccw2 <= 0;
    }

    private static int ccw(Point p1, Point p2, Point p3) {
        int ccw = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);

        if (ccw == 0) {
            return 0;
        } else if (ccw < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[py] = px;
        }
    }
}
