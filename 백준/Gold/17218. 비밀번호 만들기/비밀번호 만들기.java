import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        int[][] len = new int[str1.length + 1][str2.length + 1];
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    len[i + 1][j + 1] = len[i][j] + 1;
                } else {
                    len[i + 1][j + 1] = Math.max(len[i][j + 1], len[i + 1][j]);
                }
            }
        }

        int x = str1.length;
        int y = str2.length;
        int idx = len[x][y];

        StringBuilder answer = new StringBuilder();
        while (idx != 0) {
            while (x - 1 >= 0 && len[x - 1][y] == idx) {
                x--;
            }
            while (y - 1 >= 0 && len[x][y - 1] == idx) {
                y--;
            }
            answer.append(str1[x - 1]);
            idx -= 1;
            x -= 1;
            y -= 1;
        }

        System.out.println(answer.reverse());
    }
}
