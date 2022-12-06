/*
    Boj 3003. 킹, 퀸, 룩, 비숍, 나이트, 폰
    level. bronze 5
    solved by 송찬환
 */
package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_3003_킹퀸룩비숍나이프폰 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] piece = new int[6];
        for(int i = 0; i < 6; i++){
            piece[i] = Integer.parseInt(st.nextToken());
        }

        int[] chess = {1, 1, 2, 2, 2, 8};

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++){
            sb.append(chess[i] - piece[i] + " ");
        }
        System.out.println(sb.toString());
    }
}
