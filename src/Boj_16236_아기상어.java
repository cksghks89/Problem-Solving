/*
    Boj 16236. 아기 상어
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_16236_아기상어 {
    static int[][] map;
    static int[][] distance;
    static PriorityQueue<Integer> fish;

    static int[] babyShark;
    static int sharkWeight = 2;
    static int count = 0;
    static int eatCnt = 0;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        distance = new int[N][N];
        fish = new PriorityQueue<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur;

                if (1 <= cur && cur <= 6) {
                    fish.offer(cur);
                } else if (cur == 9) {
                    babyShark = new int[]{i, j};
                }
            }
        }

        eatFish();
        System.out.println(count);
    }

    static void eatFish() {
        while (!fish.isEmpty() && fish.peek() < sharkWeight) {
            fish.poll();

            // weight 보다 작은 물고기 거리 검사
            int min = Integer.MAX_VALUE;
            int locX = 0;
            int locY = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(1 <= map[i][j] && map[i][j] <= 6 && map[i][j] < sharkWeight){
                        int cur = bfs(i, j);
                        if(min > cur){
                            min = cur;
                            locX = i;
                            locY = j;
                        }
                    }
                }
            }

            if(min == Integer.MAX_VALUE){
                return;
            }else{
                count += min;
                map[babyShark[0]][babyShark[1]] = 0;
                babyShark = new int[]{locX, locY};
                map[locX][locY] = 9;

                eatCnt++;
                if(sharkWeight == eatCnt){
                    sharkWeight += 1;
                    eatCnt = 0;
                }
            }
        }
    }

    static int bfs(int x, int y) {
        int cnt = Integer.MAX_VALUE;
        boolean end = false;
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        Queue<int[]> pos = new LinkedList<>();
        pos.add(new int[]{x, y});
        distance[x][y] = 0;

        while (!pos.isEmpty() && !end) {
            int[] cur = pos.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isInRange(nx, ny) && distance[nx][ny] > distance[cur[0]][cur[1]] + 1) {
                    if (map[nx][ny] <= sharkWeight) {
                        distance[nx][ny] = distance[cur[0]][cur[1]] + 1;
                        pos.offer(new int[]{nx, ny});
                    }

                    if (map[nx][ny] == 9) {
                        cnt = distance[cur[0]][cur[1]] + 1;
                        end = true;
                        break;
                    }
                }
            }
        }
        return cnt;
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }
}
