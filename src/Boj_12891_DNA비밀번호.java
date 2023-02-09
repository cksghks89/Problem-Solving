/*
    Boj 12891. DNA 비밀번호
    level. silver 2
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_12891_DNA비밀번호 {
    static int[] acgtCnt = new int['T' + 1];
    static int[] acgt;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        acgt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < S; i++) {
            if (i < P) {
                acgtCnt[str.charAt(i)]++;
            } else {
                if (isPossible()) cnt++;
                acgtCnt[str.charAt(i)]++;
                acgtCnt[str.charAt(i - P)]--;
            }
        }
        if (isPossible()) cnt++;

        System.out.println(cnt);
    }

    static boolean isPossible() {
        return acgtCnt['A'] >= acgt[0] && acgtCnt['C'] >= acgt[1]
                && acgtCnt['G'] >= acgt[2] && acgtCnt['T'] >= acgt[3];
    }
}
