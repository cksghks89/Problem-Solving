import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new int[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            draw(x, y, d, g);
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1) {
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }

    private static void draw(int x, int y, int d, int g) {
        // 1. (0, 0) 을 기준으로 드래곤 커블르 그리기
        List<int[]> point = new ArrayList<>();
        point.add(new int[]{0, 0});
        point.add(new int[]{1, 0});
        int[] end = {1, 0};

        for (int i = 1; i <= g; i++) {
            point = drawCurve(point, end);
            end = new int[]{end[1] + end[0], -end[0] + end[1]};
        }

        // 2. d 방향으로 전환
        for (int i = 0; i < d; i++) {
            rotate90Reverse(point);
        }

        // 3. x, y 만큼 평행이동
        for (int[] cur : point) {
            cur[0] += x;
            cur[1] += y;
        }

        // 4. 맵에 반영하기
        for (int[] cur : point) {
            if (0 <= cur[0] && cur[0] <= 100 && 0 <= cur[1] && cur[1] <= 100) {
                map[cur[0]][cur[1]] = 1;
            }
        }
    }

    private static void rotate90Reverse(List<int[]> point) {
        for (int[] cur : point) {
            int temp = cur[0];
            cur[0] = cur[1];
            cur[1] = -temp;
        }
    }

    // 드래곤 커브 그리기
    private static List<int[]> drawCurve(List<int[]> point, int[] end) {
        List<int[]> result = new ArrayList<>();

        // 끝 점 제외하고 다시 삽입
        for (int[] cur : point) {
            result.add(new int[]{cur[0], cur[1]});
        }

        for (int[] cur : point) {
            if (cur[0] == end[0] && cur[1] == end[1]) continue;
            int[] newPoint = new int[2];

            // 위치 이동
            newPoint[0] = cur[0] - end[0];
            newPoint[1] = cur[1] - end[1];

            // 90도 회전
            int temp = newPoint[0];
            newPoint[0] = -newPoint[1];
            newPoint[1] = temp;

            // 위치 원래대로 이동
            newPoint[0] += end[0];
            newPoint[1] += end[1];

            // 결과에 포함
            result.add(newPoint);
        }

        return result;
    }
}
