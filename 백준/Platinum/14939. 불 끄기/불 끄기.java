import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean[][] bulb;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        bulb = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            String line = br.readLine();
            for (int j = 0; j < 10; j++) {
                if (line.charAt(j) == 'O') {
                    bulb[i][j] = true;
                }
            }
        }

        // bulb 의 모든 원소를 false 로 만들어야 한다.
        backTracking(0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void backTracking(int x, int y, int cnt) {
        // 경우의 수 나누기
        // 1. 내 위에가 켜져 있다면? 무조건 눌러야 함
        // 2. 내 위에가 꺼져 있다면? 무조건 누르지 말아야 함
        // 3. 첫째줄이라면? 2가지 경우 모두 하기 (단, 안누르는 것을 우선으로)

        if (x == 10) {
            if (isAllTurnOff()) {
                answer = Math.min(answer, cnt);
            }
            return;
        }

        if (x == 0) {
            backTracking(x + (y + 1) / 10, (y + 1) % 10, cnt);

            turnOnOff(x, y);
            backTracking(x + (y + 1) / 10, (y + 1) % 10, cnt + 1);
            turnOnOff(x, y);
        } else {
            if (bulb[x - 1][y]) {
                turnOnOff(x, y);
                backTracking(x + (y + 1) / 10, (y + 1) % 10, cnt + 1);
                turnOnOff(x, y);
            } else {
                backTracking(x + (y + 1) / 10, (y + 1) % 10, cnt);
            }
        }
    }

    private static void turnOnOff(int x, int y) {
        bulb[x][y] = !bulb[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isInRange(nx, ny)) {
                bulb[nx][ny] = !bulb[nx][ny];
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < 10 && 0 <= y && y < 10;
    }

    private static boolean isAllTurnOff() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (bulb[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
