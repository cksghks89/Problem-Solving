import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[][] obj;
    private static List<Integer>[] list;
    private static int[][] map;

    private static int[] dx = {0, 0, 0, -1, 1};
    private static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        obj = new int[K][4];
        list = new List[K];
        map = new int[N][N];

        for (int i = 0; i < K; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            obj[i][0] = i;
            list[i].add(i);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            obj[i][1] = r - 1;
            obj[i][2] = c - 1;
            obj[i][3] = d;
        }

        // 1. 이동 (0 ~ K-1 개의 말)
        // 2. 턴 종료 전 쌓인 말 체크 (4개면 게임 종료)
        int turn = 0;
        while (turn < 1000) {
            turn += 1;

            move();

            for (int i = 0; i < K; i++) {
                if (list[obj[i][0]].size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    private static void move() {
        // K 개의 말 이동
        for (int i = 0; i < K; i++) {
            int[] cur = obj[i];
            List<Integer> curList = list[cur[0]];

            // 가장 밑에 있는 말이 아님 -> 이동 불가
            if (curList.get(0) != i) continue;

            int nx = cur[1] + dx[cur[3]];
            int ny = cur[2] + dy[cur[3]];

            // 체스판 벗어남 == 파란색과 같음 (방향 전환)
            if (!isInRange(nx, ny) || map[nx][ny] == 2) {
                if (cur[3] == 1) cur[3] = 2;
                else if (cur[3] == 2) cur[3] = 1;
                else if (cur[3] == 3) cur[3] = 4;
                else if (cur[3] == 4) cur[3] = 3;
            }

            nx = cur[1] + dx[cur[3]];
            ny = cur[2] + dy[cur[3]];

            if (!isInRange(nx, ny)) {
                continue;
            }

            int alreadyExist = findAlreadyExist(nx, ny);

            if (map[nx][ny] == 0) {      // 흰색 칸
                if (alreadyExist == -1) {
                    changePosition(nx, ny, cur[0]);
                } else {
                    int listIdx = obj[alreadyExist][0];
                    list[listIdx].addAll(curList);
                    changePosition(nx, ny, listIdx);
                    for (int t : list[listIdx]) obj[t][0] = listIdx;
                }
            } else if (map[nx][ny] == 1) {      // 빨간색 칸
                alreadyExist = findAlreadyExist(nx, ny);
                Collections.reverse(curList);
                if (alreadyExist == -1) {
                    changePosition(nx, ny, cur[0]);
                } else {
                    int listIdx = obj[alreadyExist][0];
                    list[listIdx].addAll(curList);
                    changePosition(nx, ny, listIdx);
                    for (int t : list[listIdx]) obj[t][0] = listIdx;
                }
            }
        }
    }

    private static void changePosition(int nx, int ny, int listIdx) {
        for (int cur : list[listIdx]) {
            obj[cur][1] = nx;
            obj[cur][2] = ny;
        }
    }

    private static int findAlreadyExist(int r, int c) {
        for (int i = 0; i < K; i++) {
            if (obj[i][1] == r && obj[i][2] == c) return i;
        }
        return -1;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
