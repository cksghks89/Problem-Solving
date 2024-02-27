import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, k;
    private static int[][][] map;
    private static int[][] sharkArr;
    private static Direction[] sharkDirection;

    private static int[] dx = {0, -1, 1, 0, 0};
    private static int[] dy = {0, 0, 0, -1, 1};

    private static Set<Point> smellSet;
    private static Set<Integer> removeSet;
    private static List<int[]> nextShark;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화 --- start
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[N][N][2];
        sharkArr = new int[M + 1][2];
        sharkDirection = new Direction[M + 1];
        smellSet = new HashSet<>();
        removeSet = new HashSet<>();
        nextShark = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if (map[i][j][0] != 0) {
                    sharkArr[map[i][j][0]][0] = i;
                    sharkArr[map[i][j][0]][1] = j;
                    map[i][j][1] = k;
                    smellSet.add(new Point(i, j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int cur = Integer.parseInt(st.nextToken());
            sharkDirection[i] = new Direction(cur);
        }

        // 1-위, 2-아래, 3-왼쪽, 4-오른쪽
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= 4; j++) {
                sharkDirection[i].p[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }
        // 입력 및 초기화 --- end


        int cnt = 0;
        while (cnt < 1000) {
            cnt += 1;

            // 상어 이동 & 겹치는 상어 제거
            move();

            // 냄새 제거
            smellRemove();

            // 남아있는 상어 체크
            int remain = checkSharkCnt();

            if (remain == 1) {
                System.out.println(cnt);
                return;
            }
        }

        System.out.println(-1);
    }

    private static void move() {
        for (int i = 1; i <= M; i++) {
            if (removeSet.contains(i)) continue;
            int[] cur = sharkArr[i];
            int[] p = sharkDirection[i].p[sharkDirection[i].d];
            moveShark(i, cur, p);
        }

        for (int i = 0; i < nextShark.size(); i++) {
            int[] cur = nextShark.get(i);
            if (map[cur[1]][cur[2]][1] == k + 1) {
                removeSet.add(cur[0]);
            } else {
                map[cur[1]][cur[2]][0] = cur[0];
                map[cur[1]][cur[2]][1] = k + 1;
                smellSet.add(new Point(cur[1], cur[2]));
            }
        }

        nextShark.clear();
    }

    private static void moveShark(int id, int[] cur, int[] p) {
        for (int i = 0; i < 4; i++) {
            int nx = cur[0] + dx[p[i]];
            int ny = cur[1] + dy[p[i]];
            if (isInRange(nx, ny) && map[nx][ny][1] == 0) {
                nextShark.add(new int[]{id, nx, ny});
                sharkArr[id][0] = nx;
                sharkArr[id][1] = ny;
                sharkDirection[id].d = p[i];
                return;
            }
        }

        for (int i = 0; i < 4; i++) {
            int nx = cur[0] + dx[p[i]];
            int ny = cur[1] + dy[p[i]];
            if (isInRange(nx, ny) && map[nx][ny][0] == id) {
                nextShark.add(new int[]{id, nx, ny});
                sharkArr[id][0] = nx;
                sharkArr[id][1] = ny;
                sharkDirection[id].d = p[i];
                return;
            }
        }
    }

    private static void smellRemove() {
        Set<Point> temp = new HashSet<>();
        Iterator<Point> it = smellSet.iterator();
        while (it.hasNext()) {
            Point next = it.next();
            map[next.x][next.y][1] -= 1;
            if (map[next.x][next.y][1] == 0) {
                map[next.x][next.y][0] = 0;
                continue;
            }
            temp.add(next);
        }
        smellSet = temp;
    }

    private static int checkSharkCnt() {
        return M - removeSet.size();
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static class Direction {
        int d;
        int[][] p;

        public Direction(int d) {
            this.d = d;
            this.p = new int[5][4];
        }
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(x) * 31 + Integer.hashCode(y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point o = (Point) obj;
                return this.x == o.x && this.y == o.y;
            }
            return false;
        }
    }
}
