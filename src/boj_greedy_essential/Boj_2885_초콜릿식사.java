/*
    Boj 2885. 초콜릿 식사
    level. silver 2
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2885_초콜릿식사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        int size = 1;
        while(size < K){
            size = size * 2;
        }

        int total = size;
        int count = 0;
        while(K > 0){
            if(K >= size){
                K -= size;
            }else{
                size /= 2;
                count++;
            }
        }
        System.out.printf("%d %d", total, count);
    }
}
