/*
    Boj 2567. 색종이2
    level. silver 4
    solved by 송찬환
 */
import java.util.Scanner;

public class Boj_2567_색종이2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 스카프를 그릴 맵 생성
        int[][] map = new int[101][101];

        // 입력받는 값
        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            // 입력
            int x = sc.nextInt();
            int y = sc.nextInt();

            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    map[j + x][k + y] = 1;
                }
            }
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        // 둘레를 카운팅하기 위한 cnt 변수 선언
        int cnt = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if(map[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (!isInRange(nx, ny)) {
                        cnt++;
                        continue;
                    }
                    if (map[nx][ny] == 0) {
                        cnt++;
                    }
                }
            }
        }

        // 정답 출력
        System.out.println(cnt);
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < 100) && (0 <= y && y < 100);
    }
}
