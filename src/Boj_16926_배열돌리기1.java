/*
    Boj 16926. 배열돌리기1
    level. silver 1
    solved by 송찬환
 */
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_16926_배열돌리기1 {
    static int N, M, R;
    static int[][] map;
    static int[][] resultMap;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        resultMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 배열 돌리기
        int[] p1 = {0, 0};
        int[] p2 = {N, M};
        while (p1[0] < p2[0] && p1[1] < p2[1]) {
            // 배열 돌리기 (바깥 껍질부터 안쪽으로)
            setBound(p1[0], p1[1], p2[1] - p1[1], p2[0] - p1[0]);

            p1[0]++;
            p1[1]++;
            p2[0]--;
            p2[1]--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(resultMap[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    static void setBound(int x, int y, int w, int h) {
        Queue<Integer> rotate = rotate(getBoundList(x, y, w, h));

        // d는 방향 전환
        int nx = x, ny = y, d = 0;
        while (d < 4) {
            if (d == 0 || d == 2) {
                for (int i = 0; i < w - 1; i++) {
                    resultMap[nx][ny] = rotate.poll();
                    ny += dy[d];
                }
            } else if (d == 1 || d == 3) {
                for (int i = 0; i < h - 1; i++) {
                    resultMap[nx][ny] = rotate.poll();
                    nx += dx[d];
                }
            }
            d++;
        }
    }

    static List<Integer> getBoundList(int x, int y, int w, int h) {
        List<Integer> list = new ArrayList<>();

        // d는 방향 전환
        int nx = x, ny = y, d = 0;
        while (d < 4) {
            if (d == 0 || d == 2) {
                for (int i = 0; i < w - 1; i++) {
                    list.add(map[nx][ny]);
                    ny += dy[d];
                }
            } else if (d == 1 || d == 3) {
                for (int i = 0; i < h - 1; i++) {
                    list.add(map[nx][ny]);
                    nx += dx[d];
                }
            }
            d++;
        }
        return list;
    }

    static Queue<Integer> rotate(List<Integer> list) {
        Queue<Integer> rotateList = new ArrayDeque<>();

        for (int i = 0; i < list.size(); i++) {
            rotateList.offer(list.get((i + R) % list.size()));
        }
        return rotateList;
    }
}
