/*
    Boj 1543. 문서 검색
    level. silver 4
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1543_문서검색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String doc = br.readLine();
        String word = br.readLine();

        int idx = 0;

        int count = 0;
        while (idx < doc.length()) {
            if (doc.startsWith(word, idx)) {
                idx += word.length();
                count += 1;
                continue;
            }

            idx += 1;
        }
        System.out.println(count);
    }
}
