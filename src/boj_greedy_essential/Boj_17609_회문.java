/*
    Boj 17609. 회문
    level. gold 5
    solved by 송찬환
 */
package boj_greedy_essential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj_17609_회문 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            ArrayList<Character> s = new ArrayList<>();
            for (char c : br.readLine().toCharArray()) {
                s.add(c);
            }

            int p1 = 0;
            int p2 = s.size() - 1;
            boolean remove = false;
            int rtn = 0;

            while (p1 < p2) {
                if (s.get(p1) == s.get(p2)) {
                    p1++;
                    p2--;
                } else if (!remove) {
                    if (isPalindrome(s, p1 + 1, p2) || isPalindrome(s, p1, p2 - 1)) {
                        rtn = 1;
                    }else{
                        rtn = 2;
                    }
                    break;
                }
            }
            System.out.println(rtn);
        }
    }

    static boolean isPalindrome(ArrayList<Character> s, int start, int end) {
        int p1 = start;
        int p2 = end;

        while (p1 < p2) {
            if (s.get(p1) != s.get(p2)) {
                return false;
            }
            p1++;
            p2--;
        }
        return true;
    }
}
