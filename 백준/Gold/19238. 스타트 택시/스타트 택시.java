import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, fuel;
    private static int[][] map;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static Map<Integer, int[]> destMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        destMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;

        int passengerId = 2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int destX = Integer.parseInt(st.nextToken()) - 1;
            int destY = Integer.parseInt(st.nextToken()) - 1;

            map[startX][startY] = passengerId;
            destMap.put(passengerId, new int[]{destX, destY});
            passengerId += 1;
        }

        for (int i = 0; i < M; i++) {
            // 1. 승객 탐색
            // passenger 원소 : {승객 id, x좌표, y좌표, 거리}
            int[] passenger = searchPassenger(sx, sy);
            if (passenger == null || passenger[3] >= fuel) {
                fuel = -1;
                break;
            }

            fuel -= passenger[3];
            map[passenger[1]][passenger[2]] = 0;

            // 2. 목적지 이동
            // dest 원소 : {x좌표, y좌표, 거리}
            int[] destPos = destMap.get(passenger[0]);
            int[] dest = searchDestination(passenger[1], passenger[2], destPos[0], destPos[1]);
            if (dest == null || dest[2] > fuel) {
                fuel = -1;
                break;
            }

            fuel += dest[2];
            sx = dest[0];
            sy = dest[1];
        }

        System.out.println(fuel);
    }

    private static int[] searchDestination(int x, int y, int destX, int destY) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, 0});

        boolean[][] visited = new boolean[N][N];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (cur[0] == destX && cur[1] == destY) {
                return new int[]{cur[0], cur[1], cur[2]};
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] != 1 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
        return null;
    }

    private static int[] searchPassenger(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, 0});

        boolean[][] visited = new boolean[N][N];

        // pq 원소 : new int[]{승객 id, x좌표, y좌표, 거리}
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[3] != o2[3]) return o1[3] - o2[3];
            else if (o1[1] != o2[1]) return o1[1] - o2[1];
            else return o1[2] - o2[2];
        });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if (map[cur[0]][cur[1]] >= 2) {
                pq.add(new int[]{map[cur[0]][cur[1]], cur[0], cur[1], cur[2]});
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && map[nx][ny] != 1 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
        return pq.poll();
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
