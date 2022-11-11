/*
    Boj 16234. 인구 이동
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_16234_인구이동 {
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static int[][] visited;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Group {
        int sumOfPopular;
        int numOfCountry;
        int average;
    }

    static Map<Integer, Group> groupMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        while (true) {
            boolean isMove = move();
            if (isMove) {
                count++;
            } else {
                break;
            }
        }
        System.out.println(count);
    }

    static boolean move() {
        int groupId = 0;
        boolean isMove = false;
        initValue();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    groupId += 1;
                    makeUnion(i, j, groupId);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int curGroup = visited[i][j];

                if (map[i][j] != groupMap.get(curGroup).average) {
                    map[i][j] = groupMap.get(curGroup).average;
                    isMove = true;
                }
            }
        }

        return isMove;
    }

    static void makeUnion(int x, int y, int groupId) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (visited[cur.x][cur.y] != 0) {
                continue;
            }
            visited[cur.x][cur.y] = groupId;

            Group group = groupMap.getOrDefault(groupId, new Group());
            group.numOfCountry += 1;
            group.sumOfPopular += map[cur.x][cur.y];
            group.average = group.sumOfPopular / group.numOfCountry;
            groupMap.put(groupId, group);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isInRange(nx, ny)) {
                    if (isInLR(cur.x, cur.y, nx, ny) && visited[nx][ny] == 0) {
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }

    static boolean isInLR(int x, int y, int nx, int ny) {
        int diff = Math.abs(map[x][y] - map[nx][ny]);
        return (L <= diff && diff <= R);
    }

    static void initValue(){
        visited = new int[N][N];
        groupMap = new HashMap<>();
        for(int i = 0; i < N; i++){
            Arrays.fill(visited[i], 0);
        }
    }
}
