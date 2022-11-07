/*
    Boj 15686. 치킨 배달
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_15686_치킨배달 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] map;

    static List<Point> home;
    static List<Point> chicken;

    static boolean[] choiceStore;

    static int cityChickenDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur;

                if (cur == 1) {
                    home.add(new Point(i, j));
                } else if (cur == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }
        choiceStore = new boolean[chicken.size()];

        combination(0, chicken.size(), M, 0);

        System.out.println(cityChickenDistance);
    }

    static void combination(int depth, int n, int r, int s) {
        if (depth == r) {
            // 총 거리 계산
            cityChickenDistance = Math.min(cityChickenDistance, calcDistance());
            return;
        }

        for (int i = s; i < n; i++) {
            if (!choiceStore[i]) {
                choiceStore[i] = true;
                combination(depth + 1, n, r, i + 1);
                choiceStore[i] = false;
            }
        }
    }

    static int calcDistance(){
        int totalDistance = 0;

        for(int i = 0; i < home.size(); i++){
            Point curHome = home.get(i);

            int curMinDistance = Integer.MAX_VALUE;

            for(int j = 0; j < chicken.size(); j++){
                if(choiceStore[j]){
                    Point curChicken = chicken.get(j);

                    curMinDistance = Math.min(curMinDistance,
                            Math.abs(curHome.x - curChicken.x) + Math.abs(curHome.y - curChicken.y));
                }
            }

            totalDistance += curMinDistance;
        }

        return totalDistance;
    }
}
