/*
    Boj 14499. 주사위 굴리기
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_14499_주사위굴리기 {
    static int N, M;
    static int x, y;
    static int K;

    static int[][] map;
    static Queue<Integer> operation;

    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    static Dice dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        operation = new LinkedList<>();
        dice = new Dice();

        // map 구성
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 이동명령 구성
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            operation.offer(Integer.parseInt(st.nextToken()));
        }

        moveDice();
    }

    static void moveDice() {
        while(!operation.isEmpty()){
            int cur = operation.poll();

            int nx = x + dx[cur];
            int ny = y + dy[cur];

            if(isInRange(nx, ny)){
                dice.operation(cur);

                if(map[nx][ny] == 0){
                    map[nx][ny] = dice.bottom;
                }else{
                    dice.bottom = map[nx][ny];
                    map[nx][ny] = 0;
                }
                x = nx;
                y = ny;
                System.out.println(dice.top);
            }
        }
    }

    static boolean isInRange(int x, int y){
        return (0 <= x && x < N) && (0 <= y && y < M);
    }
}

class Dice {
    int top;
    int bottom;
    int left;
    int right;
    int front;
    int back;

    public Dice() {
        this.top = 0;
        this.bottom = 0;
        this.left = 0;
        this.right = 0;
        this.front = 0;
        this.back = 0;
    }

    public void operation(int op){
        switch(op){
            case 1:
                moveEast();
                break;
            case 2:
                moveWest();
                break;
            case 3:
                moveNorth();
                break;
            case 4:
                moveSouth();
                break;
        }
    }

    // left, right 은 바뀌지 않음
    public void moveNorth(){
        int temp = this.top;

        this.top = this.front;
        this.front = this.bottom;
        this.bottom = this.back;
        this.back = temp;
    }

    // front, back 은 바뀌지 않음
    public void moveEast(){
        int temp = this.top;

        this.top = this.left;
        this.left = this.bottom;
        this.bottom = this.right;
        this.right = temp;
    }

    public void moveWest(){
        int temp = this.top;

        this.top = this.right;
        this.right = this.bottom;
        this.bottom = this.left;
        this.left = temp;
    }

    public void moveSouth(){
        int temp = this.top;

        this.top = this.back;
        this.back = this.bottom;
        this.bottom = this.front;
        this.front = temp;
    }
}
