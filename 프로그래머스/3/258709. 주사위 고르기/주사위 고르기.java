import java.util.*;

class Solution {
    private int[] diceA;
    private int[] diceB;
    private int[][] dice;
    private int n, r;
    private int victory;
    private int[] answer;

    public int[] solution(int[][] d) {
        // 초기화 ---- start
        dice = d;
        n = dice.length;
        r = n / 2;
        diceA = new int[r];
        diceB = new int[r];
        victory = 0;
        // 초기화 ---- end


        // 구현부 ---- start
        // 완전탐색 - 조합(뽑을 수 있는 모든 주사위 경우의 수) & 중복순열(모든 주사위 합 경우의 수)
        comb(0, 0);
        // 구현부 ---- end


        // 출력부
        for (int i = 0; i < r; i++) answer[i] += 1;
        return answer;
    }


    // 조합 - 모든 주사위 경우의 수를 탐색
    private void comb(int s, int cnt) {
        if (cnt == r) {
            // diceB 를 배열로
            int idx = 0;
            for (int i = 0; i < n; i++) {
                boolean isIn = false;
                for (int item : diceA) {
                    if (item == i) isIn = true;
                }

                if (!isIn) diceB[idx++] = i;
            }

            // diceA 와 diceB의 모든 경우의 수를 계산
            fight();
            return;
        }

        for (int i = s; i < n; i++) {
            diceA[cnt] = i;
            comb(i + 1, cnt + 1);
        }
    }

    // A가 이기는 경우의 수를 계산하는 모듈
    private void fight() {
        int[] aTable = new int[501];
        int[] bTable = new int[501];

        int[] out = new int[r];
        perm(0, aTable, out, diceA);
        perm(0, bTable, out, diceB);

        int winCnt = 0;
        for (int i = 0; i < aTable.length; i++) {
            if (aTable[i] == 0) continue;

            int[] fightResult = getFightResult(i, bTable);
            winCnt += aTable[i] * fightResult[0];
        }

        if (victory < winCnt) {
            victory = winCnt;
            answer = Arrays.copyOf(diceA, diceA.length);
        }
    }

    // 중복 순열 - 각 주사위의 합이 나올 수 있는 경우의 수를 모두 계산한다.
    private void perm(int cnt, int[] cntTable, int[] out, int[] curDice) {
        if (cnt == r) {
            int num = 0;
            for (int i = 0; i < r; i++) {
                num += dice[curDice[i]][out[i]];
            }
            cntTable[num] += 1;
            return;
        }

        for (int i = 0; i < 6; i++) {
            out[cnt] = i;
            perm(cnt + 1, cntTable, out, curDice);
        }
    }

    // 승, 무, 패의 경우를 계산
    private int[] getFightResult(int num, int[] table) {
        int win = 0;
        int draw = 0;
        int lose = 0;

        for (int i = 0; i < table.length; i++) {
            if (table[i] == 0) continue;

            if (i < num) win += table[i];
            else if (i == num) draw += table[i];
            else lose += table[i];
        }

        return new int[]{win, draw, lose};
    }
}