import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] fields;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static final int EMPTY = -2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fields = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            List<int[]> list = new ArrayList<>();

            int[] prev = new int[2];
            int count = 0;
            int prevRainbowBlockCount = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int curRainbowBlockCount = 0;

                    if (fields[i][j] <= 0) continue;
                    List<int[]> blockList = bfs(i, j, fields[i][j]);

                    if (blockList.size() < 2) continue;

                    for (int k = 0; k < blockList.size(); k++) {
                        int[] cur = blockList.get(k);
                        if (fields[cur[0]][cur[1]] == 0) curRainbowBlockCount += 1;
                    }

                    int[] cur = blockList.get(0);

                    if ((count < blockList.size()) || (count == blockList.size() && curRainbowBlockCount > prevRainbowBlockCount)) {
                        count = blockList.size();
                        list = blockList;
                        prevRainbowBlockCount = curRainbowBlockCount;
                        prev = cur;
                    }

                    if (count == blockList.size() && curRainbowBlockCount == prevRainbowBlockCount) {
                        if (prev[0] < cur[0] || (prev[0] == cur[0] && prev[1] < cur[1])) {
                            list = blockList;
                            prev = cur;
                        }
                    }
                }
            }

            if (list.isEmpty()) break;

            answer += count * count;
            list.forEach(o -> fields[o[0]][o[1]] = EMPTY);

            gravity();
            reverseRotate();
            gravity();
        }

        System.out.println(answer);
    }

    private static List<int[]> bfs(int x, int y, int key) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        boolean[][] visited = new boolean[N][N];

        List<int[]> blockList = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            blockList.add(new int[]{cur[0], cur[1]});

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && (fields[nx][ny] == key || fields[nx][ny] == 0)) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        blockList.sort((o1, o2) -> {
            if (o1[0] != o2[0]) return o2[0] - o1[0];
            else return o2[1] - o1[1];
        });

        for (int i = 0; i < blockList.size(); i++) {
            if (fields[blockList.get(i)[0]][blockList.get(i)[1]] == 0) continue;
            int[] remove = blockList.remove(i);
            blockList.add(0, remove);
        }

        return blockList;
    }

    private static void gravity() {
        int[][] newFields = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newFields[i][j] = EMPTY;
            }
        }

        for (int i = 0; i < N; i++) {
            Queue<Integer> queue = new ArrayDeque<>();
            int prevBlackBlock = N;
            for (int j = N - 1; j >= 0; j--) {
                if (fields[j][i] == -1) {
                    queueing(i, prevBlackBlock, queue, newFields);
                    prevBlackBlock = j;
                    newFields[j][i] = -1;
                    continue;
                }

                if (fields[j][i] != EMPTY) {
                    queue.offer(fields[j][i]);
                }
            }

            if (!queue.isEmpty()) {
                queueing(i, prevBlackBlock, queue, newFields);
            }
        }

        fields = newFields;
    }

    private static void queueing(int col, int prevBlackBlock, Queue<Integer> queue, int[][] fields) {
        int row = prevBlackBlock - 1;
        while (!queue.isEmpty()) {
            fields[row--][col] = queue.poll();
        }
    }

    private static void reverseRotate() {
        int[][] newFields = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newFields[N - 1 - j][i] = fields[i][j];
            }
        }
        fields = newFields;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
