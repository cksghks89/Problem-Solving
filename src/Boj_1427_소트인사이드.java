/*
    Boj 1427. 소트인사이드
    level. silver 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_1427_소트인사이드 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] N = br.readLine().toCharArray();

        Arrays.sort(N);

        StringBuilder sb = new StringBuilder();
        for(int i = N.length - 1; i >= 0; i--){
            sb.append(N[i]);
        }
        System.out.println(sb.toString());
    }
}
