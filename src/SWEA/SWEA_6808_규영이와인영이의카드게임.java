package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SWEA_6808_규영이와인영이의카드게임 {
    static int[] kyu;
    static int[] in;
    static int[] out;
    static int kyuWinCnt;
    static int kyuLoseCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            // 입력
            kyu = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
            in = IntStream.rangeClosed(1, 18).filter(x -> Arrays.binarySearch(kyu, x) < 0).toArray();

            // 초기화
            out = new int[9];
            kyuWinCnt = 0;
            kyuLoseCnt = 0;

            // 순열
            perm(0, 0);

            // 출력부
            sb.append(String.format("#%d %d %d\n", tc, kyuWinCnt, kyuLoseCnt));
        }
        System.out.print(sb.toString());
    }

    static void perm(int cnt, int flag) {
        if (cnt == 9) {
            play(out);
            return;
        }
        for (int i = 0; i < 9; i++) {
            if ((flag & (1 << i)) != 0) continue;
            out[cnt] = in[i];
            perm(cnt + 1, flag | (1 << i));
        }
    }

    static void play(int[] in) {
        int kyuScore = 0;
        int inScore = 0;

        for (int i = 0; i < 9; i++) {
            if(in[i] > kyu[i]){
                inScore += in[i] + kyu[i];
            }else if(in[i] < kyu[i]){
                kyuScore += in[i] + kyu[i];
            }
        }
        if (kyuScore > inScore) kyuWinCnt++;
        if (kyuScore < inScore) kyuLoseCnt++;
    }
}
