/*
    Boj 5086. 배수와 약수
    level. bronze 3
    solved by 송찬환
 */
package boj_bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_5086_배수와약수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0){
                break;
            }

            if(a <= b && b % a == 0){
                sb.append("factor");
            }else if(a > b && a % b == 0){
                sb.append("multiple");
            }else{
                sb.append("neither");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
