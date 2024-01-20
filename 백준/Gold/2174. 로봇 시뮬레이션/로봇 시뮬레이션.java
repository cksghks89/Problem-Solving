import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int A, B;
    private static int N, M;

    private static boolean[][] map;
    private static List<int[]> robots;

    private static Map<String, Integer> convert;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[A + 1][B + 1];
        robots = new ArrayList<>();
        convert = new HashMap<>();
        convert.put("N", 0);
        convert.put("W", 1);
        convert.put("S", 2);
        convert.put("E", 3);

        robots.add(null);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] cur = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), convert.get(st.nextToken())};
            robots.add(cur);
            map[cur[0]][cur[1]] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken());
            String op = st.nextToken();
            int repeat = Integer.parseInt(st.nextToken());

            int result = operation(idx, op, repeat);
            if (result == 1) {
                System.out.printf("Robot %d crashes into the wall\n", idx);
                return;
            } else if (result == 2) {
                int robot = findRobot(idx);
                System.out.printf("Robot %d crashes into robot %d\n", idx, robot);
                return;
            }
        }
        System.out.println("OK");
    }

    private static int operation(int idx, String op, int repeat) {
        switch (op) {
            case "L":
                L(idx, repeat);
                break;
            case "R":
                R(idx, repeat);
                break;
            case "F":
                int result = F(idx, repeat);
                if (result != 0) return result;
                break;
        }
        return 0;
    }

    private static void L(int idx, int repeat) {
        int[] cur = robots.get(idx);
        repeat = repeat % 4;
        cur[2] = (cur[2] + repeat) % 4;
    }

    private static void R(int idx, int repeat) {
        int[] cur = robots.get(idx);
        repeat = repeat % 4;
        cur[2] = (cur[2] - repeat + 4) % 4;
    }

    private static int F(int idx, int repeat) {
        int[] cur = robots.get(idx);

        for (int i = 0; i < repeat; i++) {
            map[cur[0]][cur[1]] = false;
            switch (cur[2]) {
                case 0:
                    cur[1] += 1;
                    break;
                case 1:
                    cur[0] -= 1;
                    break;
                case 2:
                    cur[1] -= 1;
                    break;
                case 3:
                    cur[0] += 1;
                    break;
            }
            boolean inRange = isInRange(cur[0], cur[1]);

            if (inRange) {
                if (map[cur[0]][cur[1]]) {
                    return 2;
                }
                map[cur[0]][cur[1]] = true;
            } else {
                return 1;
            }
        }
        return 0;
    }

    private static int findRobot(int idx) {
        int[] cur = robots.get(idx);

        for (int i = 1; i <= N; i++) {
            if (i == idx) continue;
            if (cur[0] == robots.get(i)[0] && cur[1] == robots.get(i)[1]) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return (1 <= x && x <= A && 1 <= y && y <= B);
    }

}
