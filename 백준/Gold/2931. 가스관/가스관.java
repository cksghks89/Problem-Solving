import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C;
    private static char[][] map;
    private static int[] M;
    private static int[] Z;

    private static boolean[][][] visited;

    private static char[][] copyMap;
    private static boolean[][][] copyVisited;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static char[] block = {'|', '-', '1', '2', '3', '4'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C][2];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'M') M = new int[]{i, j};
                if (map[i][j] == 'Z') Z = new int[]{i, j};
            }
        }


        // 1. 가스가 새는 곳 찾기
        int[] rmBlock = new int[2];
        for (int i = 0; i < 4; i++) {
            int nx = M[0] + dx[i];
            int ny = M[1] + dy[i];
            if (isInRange(nx, ny) && map[nx][ny] != '.') {
                rmBlock = bfs(nx, ny, M[0], M[1], M[0] == nx ? 0 : 1);
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(rmBlock[0] + 1).append(' ').append(rmBlock[1] + 1).append(' ');
        // 2. 해당 블록에 모든 불록 넣어보기 (+는 제외)
        for (int i = 0; i < block.length; i++) {
            copy();
            copyMap[rmBlock[0]][rmBlock[1]] = block[i];
            boolean result = bfsAfterBlockInsert(rmBlock[0], rmBlock[1], rmBlock[2], rmBlock[3], rmBlock[4]);
            if (result && pipeLineCheck(copyMap, copyVisited)) {
                sb.append(block[i]);
                System.out.println(sb);
                return;
            }
        }

        sb.append('+');
        System.out.println(sb);
    }

    private static boolean bfsAfterBlockInsert(int x, int y, int px, int py, int d) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, px, py, d});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (copyVisited[cur[0]][cur[1]][cur[4]]) return false;
            else if (cur[0] == Z[0] && cur[1] == Z[1]) return true;

            copyVisited[cur[0]][cur[1]][cur[4]] = true;

            int[] next = getNextPosition(cur[0], cur[1], cur[2], cur[3], cur[4], copyMap);
            if (!isInRange(next[0], next[1]) || copyMap[next[0]][next[1]] == '.') return false;
            queue.offer(new int[]{next[0], next[1], cur[0], cur[1], next[0] == cur[0] ? 0 : 1});
        }
        return false;
    }

    private static int[] bfs(int x, int y, int px, int py, int d) {
        Queue<int[]> queue = new ArrayDeque<>();
        // 가로 방향 = 0, 세로 방향 = 1, px, py = 이전 블록
        queue.offer(new int[]{x, y, px, py, d});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]][cur[4]]) continue;
            visited[cur[0]][cur[1]][cur[4]] = true;

            int[] next = getNextPosition(cur[0], cur[1], cur[2], cur[3], cur[4], map);
            if (map[next[0]][next[1]] == '.') {
                return new int[]{next[0], next[1], cur[0], cur[1], next[0] == cur[0] ? 0 : 1};
            }
            queue.offer(new int[]{next[0], next[1], cur[0], cur[1], next[0] == cur[0] ? 0 : 1});
        }
        return null;
    }

    private static int[] getNextPosition(int x, int y, int px, int py, int d, char[][] map) {
        char block = map[x][y];

        if (block == '+') {
            block = d == 0 ? '-' : '|';
        }

        switch (block) {
            case '|':
                if (!(x - 1 == px && y == py) && !(x + 1 == px && y == py)) return new int[]{-1, -1};
                if (x - 1 == px) return new int[]{x + 1, y};
                else return new int[]{x - 1, y};
            case '-':
                if (!(x == px && y - 1 == py) && !(x == px && y + 1 == py)) return new int[]{-1, -1};
                if (y - 1 == py) return new int[]{x, y + 1};
                else return new int[]{x, y - 1};
            case '1':
                if (!(x == px && y + 1 == py) && !(x + 1 == px && y == py)) return new int[]{-1, -1};
                if (x + 1 == px) return new int[]{x, y + 1};
                else return new int[]{x + 1, y};
            case '2':
                if (!(x - 1 == px && y == py) && !(x == px && y + 1 == py)) return new int[]{-1, -1};
                if (x - 1 == px) return new int[]{x, y + 1};
                else return new int[]{x - 1, y};
            case '3':
                if (!(x - 1 == px && y == py) && !(x == px && y - 1 == py)) return new int[]{-1, -1};
                if (x - 1 == px) return new int[]{x, y - 1};
                else return new int[]{x - 1, y};
            case '4':
                if (!(x == px && y - 1 == py) && !(x + 1 == px && y == py)) return new int[]{-1, -1};
                if (y - 1 == py) return new int[]{x + 1, y};
                else return new int[]{x, y - 1};
        }
        return new int[]{-1, -1};
    }

    private static void copy() {
        copyMap = new char[R][C];
        copyVisited = new boolean[R][C][2];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copyMap[i][j] = map[i][j];
                copyVisited[i][j][0] = visited[i][j][0];
                copyVisited[i][j][1] = visited[i][j][1];
            }
        }
    }

    private static boolean pipeLineCheck(char[][] map, boolean[][][] visited) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.' || map[i][j] == 'Z' || map[i][j] == 'M') continue;
                if (map[i][j] == '+' && (!visited[i][j][0] || !visited[i][j][1])) return false;
                if (!visited[i][j][0] && !visited[i][j][1]) return false;
            }
        }
        return true;
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
