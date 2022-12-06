/*
    Boj 3009. 네 번째 점
    level. bronze 3
    solved by 송찬환
 */
package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_3009_네번째점 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int x;
        int y;

        int[] pointX = new int[3];
        int[] pointY = new int[3];
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            pointX[i] = Integer.parseInt(st.nextToken());
            pointY[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(pointX);
        Arrays.sort(pointY);
        x = pointX[0];
        y = pointY[0];
        if(x == pointX[1]){
            x = pointX[2];
        }
        if(y == pointY[1]){
            y = pointY[2];
        }
        System.out.println(x + " "+ y);
    }
}
