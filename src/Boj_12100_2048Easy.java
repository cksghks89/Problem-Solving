/*
    Boj 12100. 2048 Easy
    level. gold 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_12100_2048Easy {
    static int N;
    static int[] out;
    static int max;
    static int[][] baseMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        out = new int[5];

        baseMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                baseMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        perm(0);
        System.out.println(max);
    }

    static void perm(int cnt) {
        if (cnt == 5) {
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                System.arraycopy(baseMap[i], 0, map[i], 0, N);
            }
            for (int i = 0; i < 5; i++) {
                map = tilt(map, out[i]);
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            out[cnt] = i;
            perm(cnt + 1);
        }
    }

    static int[][] tilt(int[][] map, int mode) {
        switch (mode) {
            case 0:     // 상
                map = rotate(map);
                map = rotate(map);
                map = rotate(map);
                merge(map);
                map = rotate(map);
                break;
            case 1:     // 우
                map = rotate(map);
                map = rotate(map);
                merge(map);
                map = rotate(map);
                map = rotate(map);
                break;
            case 2:     // 하
                map = rotate(map);
                merge(map);
                map = rotate(map);
                map = rotate(map);
                map = rotate(map);
                break;
            case 3:     // 좌
                merge(map);
                break;
        }
        return map;
    }

    private static void merge(int[][] map) {
        for (int i = 0; i < N; i++) {
            map[i] = lineMerge(map[i]);
        }
    }

    static int[][] rotate(int[][] map) {
        int[][] rotateMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotateMap[j][N - i - 1] = map[i][j];
            }
        }
        return rotateMap;
    }

    static int[] lineMerge(int[] line) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (line[i] == 0) continue;
            queue.offer(line[i]);
        }

        int idx = 0;
        int[] mergeLine = new int[N];

        while (queue.size() >= 2) {
            int cur = queue.poll();
            if (cur == queue.peek()) {
                queue.poll();
                mergeLine[idx++] = cur * 2;
            } else {
                mergeLine[idx++] = cur;
            }
        }
        if (!queue.isEmpty()) {
            mergeLine[idx] = queue.poll();
        }

        return mergeLine;
    }
}
