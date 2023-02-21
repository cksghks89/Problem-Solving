/*
    Boj 6987. 월드컵
    level. gold 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_6987_월드컵 {
    static ArrayList<int[]> allGame;
    static int[][] input;
    static int[][] counting;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        allGame = new ArrayList<>();
        comb(0, 0, new int[2]);

        input = new int[6][3];
        counting = new int[6][3];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    input[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            result = 0;
            backtracking(0);
            System.out.print(result + " ");
        }
        System.out.println();
    }

    static void backtracking(int cnt) {
        if (cnt == allGame.size()) {
            if (isCorrect()) result = 1;
            return;
        }

        int[] curGame = allGame.get(cnt);
        
        // 첫 팀이 이기는 경우
        counting[curGame[0]][0] += 1;
        counting[curGame[1]][2] += 1;
        if (isPromise()) {
            backtracking(cnt + 1);
        }
        counting[curGame[0]][0] -= 1;
        counting[curGame[1]][2] -= 1;

        // 둘째 팀이 이기는 경우
        counting[curGame[0]][2] += 1;
        counting[curGame[1]][0] += 1;
        if (isPromise()) {
            backtracking(cnt + 1);
        }
        counting[curGame[0]][2] -= 1;
        counting[curGame[1]][0] -= 1;
        
        // 무승부인 경우
        counting[curGame[0]][1] += 1;
        counting[curGame[1]][1] += 1;
        if (isPromise()) {
            backtracking(cnt + 1);
        }
        counting[curGame[0]][1] -= 1;
        counting[curGame[1]][1] -= 1;
    }

    // 가지치기를 위한 메서드 (현재까지 경기 결과가 입력값보다 커지면 더이상 진행하지 않음)
    static boolean isPromise() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (counting[i][j] > input[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 게임결과가 정확히 같은지 체크
    static boolean isCorrect() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (counting[i][j] != input[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 조합수를 통해 가능한 모든 경기를 allGame 에 뽑아낸다
    static void comb(int cnt, int start, int[] out) {
        if (cnt == 2) {
            allGame.add(Arrays.copyOf(out, 2));
            return;
        }

        for (int i = start; i < 6; i++) {
            out[cnt] = i;
            comb(cnt + 1, i + 1, out);
        }
    }
}
