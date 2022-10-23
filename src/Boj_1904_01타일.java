/*
    Boj 1904. 01타일
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1904_01타일 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int n1 = 1;
        int n2 = 2;

        int answer = 0;
        if(N == 1){
            answer = 1;
        }else if(N == 2){
            answer = 2;
        }

        for(int i = 3; i <= N; i++){
            answer = (n1 + n2) % 15746;

            n1 = n2;
            n2 = answer;
        }

        System.out.println(answer);
    }
}
