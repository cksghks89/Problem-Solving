/*
    Boj 9252. LCS 2
    level. gold 4
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_9252_LCS2 {
    static char[] str1, str2;
    static int[][] lcs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = (" " + br.readLine().trim()).toCharArray();
        str2 = (" " + br.readLine().trim()).toCharArray();

        lcs = new int[str1.length][str2.length];

        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    if (lcs[i - 1][j] < lcs[i][j - 1]) {
                        lcs[i][j] = lcs[i][j - 1];
                    } else {
                        lcs[i][j] = lcs[i - 1][j];
                    }
                }
            }
        }

        if (lcs[str1.length - 1][str2.length - 1] == 0) {
            System.out.println(0);
            return;
        }

        String ans = getLcsString();
        System.out.println(ans.length());
        System.out.println(ans);
    }

    static String getLcsString() {
        int x = str1.length - 1;
        int y = str2.length - 1;

        StringBuilder lcsStr = new StringBuilder();
        while (x != 0 && y != 0) {
            if (lcs[x][y] == lcs[x - 1][y]) {
                x = x - 1;
                continue;
            }

            if (lcs[x][y] == lcs[x][y - 1]) {
                y = y - 1;
                continue;
            }

            lcsStr.append(str1[x]);
            x = x - 1;
            y = y - 1;
        }

        return lcsStr.reverse().toString();
    }
}
