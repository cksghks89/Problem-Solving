/*
    Boj 12904. A와 B
    level. gold 5
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_12904_A와B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // S 에서 추가하는 것이 아닌 T 에서 제거하는 방식으로 구현
        String S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());

        while (S.length() != T.length()) {
            if (T.charAt(T.length() - 1) == 'A') {
                T.deleteCharAt(T.length() - 1);
            } else if (T.charAt(T.length() - 1) == 'B') {
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }
        if(S.equals(T.toString())){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
}
