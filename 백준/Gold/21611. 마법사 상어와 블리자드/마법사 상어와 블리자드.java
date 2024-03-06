import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] map;

    private static int[] removed;

    private static int[] moveDx = {0, 1, 0, -1};
    private static int[] moveDy = {-1, 0, 1, 0};
    private static int[] blizzardDx = {0, -1, 1, 0, 0};
    private static int[] blizzardDy = {0, 0, 0, -1, 1};

    private static int[] cntArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        removed = new int[4];
        cntArr = new int[2500];

        for (int i = 0; i < 2500; i++) {
            cntArr[i] = i / 2 + 1;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            // 1. 블리자드 마법
            blizzard(d, s);

            // 2. 이동 -> 4개 이상 폭발 반복 (폭발이 없을 때까지)
            while (true) {
                move();
                if (!remove()) break;
            }

            // 3. 증식 (개수, 그룹번호)
            plus();
        }

        System.out.println(removed[1] + removed[2] * 2 + removed[3] * 3);
    }

    private static void plus() {
        Queue<Integer> queue = new ArrayDeque<>();
        int x = N / 2;
        int y = N / 2;

        int idx = 0;
        int cnt = 0;
        int d = 0;
        while (true) {
            x += moveDx[d];
            y += moveDy[d];
            cnt += 1;
            if (cnt == cntArr[idx]) {
                idx += 1;
                d = (d + 1) % 4;
                cnt = 0;
            }

            if (!isInRange(x, y)) break;

            if (map[x][y] != 0) queue.offer(map[x][y]);
        }

        int groupId = !queue.isEmpty() ? queue.poll() : 0;
        int groupCnt = 1;
        Queue<Integer> insertQueue = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (groupId != cur) {
                insertQueue.offer(groupCnt);
                insertQueue.offer(groupId);
                groupId = cur;
                groupCnt = 1;
            } else {
                groupCnt += 1;
            }

            if (queue.isEmpty()) {
                insertQueue.offer(groupCnt);
                insertQueue.offer(groupId);
            }
        }

        map = new int[N][N];

        x = N / 2;
        y = N / 2;
        idx = 0;
        cnt = 0;
        d = 0;
        while (!insertQueue.isEmpty()) {
            int cur = insertQueue.poll();

            x += moveDx[d];
            y += moveDy[d];
            cnt += 1;
            if (cnt == cntArr[idx]) {
                idx += 1;
                d = (d + 1) % 4;
                cnt = 0;
            }

            if (!isInRange(x, y)) break;
            map[x][y] = cur;
        }
    }

    private static boolean remove() {
        int x = N / 2;
        int y = N / 2;

        int idx = 0;
        int cnt = 0;
        int d = 0;

        int groupId = 0;
        List<int[]> list = new ArrayList<>();

        boolean isRemoved = false;
        while (true) {
            x += moveDx[d];
            y += moveDy[d];
            cnt += 1;
            if (cnt == cntArr[idx]) {
                idx += 1;
                d = (d + 1) % 4;
                cnt = 0;
            }

            if (!isInRange(x, y)) break;

            if (map[x][y] != groupId) {
                if (list.size() >= 4) {
                    for (int[] point : list) {
                        removed[map[point[0]][point[1]]] += 1;
                        map[point[0]][point[1]] = 0;
                    }
                    isRemoved = true;
                }
                groupId = map[x][y];
                list.clear();
                list.add(new int[]{x, y});
            } else {
                list.add(new int[]{x, y});
            }
        }

        return isRemoved;
    }

    private static void move() {
        Queue<Integer> queue = new ArrayDeque<>();

        int x = N / 2;
        int y = N / 2;

        int idx = 0;
        int cnt = 0;
        int d = 0;
        while (true) {
            x += moveDx[d];
            y += moveDy[d];
            cnt += 1;
            if (cnt == cntArr[idx]) {
                idx += 1;
                d = (d + 1) % 4;
                cnt = 0;
            }

            if (!isInRange(x, y)) break;

            if (map[x][y] != 0) queue.offer(map[x][y]);
        }

        map = new int[N][N];

        x = N / 2;
        y = N / 2;
        idx = 0;
        cnt = 0;
        d = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            x += moveDx[d];
            y += moveDy[d];
            cnt += 1;
            if (cnt == cntArr[idx]) {
                idx += 1;
                d = (d + 1) % 4;
                cnt = 0;
            }

            if (!isInRange(x, y)) break;
            map[x][y] = cur;
        }
    }

    private static void blizzard(int d, int s) {
        int x = N / 2;
        int y = N / 2;

        for (int i = 0; i < s; i++) {
            x += blizzardDx[d];
            y += blizzardDy[d];

            if (isInRange(x, y)) {
                map[x][y] = 0;
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
