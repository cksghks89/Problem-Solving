/*
    Boj 1358. 하키
    level. silver 4
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1358_하키 {
    static int X;
    static int Y;
    static int W;
    static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[][] arr = new int[P][2];

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            int tx = arr[i][0];
            int ty = arr[i][1];

            if ((tx - X) * (tx - X) + (ty - (Y + H / 2)) * (ty - (Y + H / 2)) <= (H / 2) * (H / 2) && tx < X)
                cnt++;
            else if (X <= tx && tx <= X + W && Y <= ty && ty <= Y + H)
                cnt++;
            else if ((tx - (X + W)) * (tx - (W + X)) + (ty - (Y + H / 2)) * (ty - (Y + H / 2)) <= (H / 2) * (H / 2) && X + W < tx)
                cnt++;
        }
        System.out.println(cnt);
    }
}
