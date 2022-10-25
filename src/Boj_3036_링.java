/*
    Boj 3036. 링
    level. silver 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_3036_링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] ring = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            ring[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N; i++) {
            int[] cur = divideNum(ring[0], ring[i]);
            sb.append(cur[0]).append("/").append(cur[1]).append("\n");
        }
        System.out.print(sb.toString());
    }
    static int[] divideNum(int a, int b){
        int minNum = Math.min(a, b);

        for(int i = 2; i <= minNum; i++){
            if(a % i == 0 && b % i == 0){
                a = a / i;
                b = b / i;

                minNum = Math.min(a, b);
                i = 1;
            }
        }
        return new int[]{a, b};
    }
}
