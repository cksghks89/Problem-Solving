/*
    Boj 25501. 재귀의 귀재
    level. bronze 2
    solved by 송찬환
 */
package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_25501_재귀의귀재 {
    static int cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int test = 0; test < T; test++){
            char[] s = br.readLine().toCharArray();
            cnt = 1;
            sb.append(recursion(s, 0, s.length-1) + " ");
            sb.append(cnt + "\n");
        }
        System.out.print(sb.toString());
    }

    static int recursion(char[] s, int l, int r){
        if(l >= r){
            return 1;
        }else if(s[l] != s[r]){
            return 0;
        }else{
            cnt += 1;
            return recursion(s, l+1, r-1);
        }
    }


}
