import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        // 초기화 ---- start
        int n = cards.length;

        Map<Integer, Integer> initMap = new HashMap<>();
        Map<Integer, Integer> seeCardMap = new HashMap<>();

        int pairCnt = 0;

        for (int i = 0; i < n / 3; i++) {
            if (initMap.containsKey(n + 1 - cards[i])) {
                pairCnt += 1;
                initMap.remove(n + 1 - cards[i]);
            } else {
                initMap.put(cards[i], 0);
            }
        }
        // 초기화 ---- end


        // 구현부 -- start
        // 그리디 방식으로 문제풀이
        // 경우의 수 1. coin을 하나만 사용하여 n + 1 만들기 (기존 보유 카드 + 새로 오픈한 카드)
        // 경우의 수 2. coin을 두개 사용하여 n + 1 만들기
        int maxRound = pairCnt + 1;

        for (int i = n / 3; i <= getSeeIdx(n, maxRound); i++) {
            seeCardMap.put(cards[i], 0);
        }

        boolean allStop = false;
        while (true) {
            int before = maxRound;

            // 경우의 수 1.
            if (coin < 1) break;
            boolean isPass = false;
            Set<Integer> initKeySet = initMap.keySet();
            for (int cur : initKeySet) {
                if (seeCardMap.containsKey(n + 1 - cur)) {
                    initMap.remove(cur);
                    seeCardMap.remove(n + 1 - cur);
                    coin -= 1;
                    maxRound += 1;

                    if (getSeeIdx(n, maxRound) < n) {
                        seeCardMap.put(cards[getSeeIdx(n, maxRound)], 0);
                        seeCardMap.put(cards[getSeeIdx(n, maxRound) - 1], 0);
                    } else {
                        allStop = true;
                    }

                    isPass = true;
                    break;
                }
            }

            if (allStop) break;
            if (isPass) continue;


            // 경우의 수 2.
            if (coin < 2) break;
            Set<Integer> seeCardSet = seeCardMap.keySet();
            for (int cur : seeCardSet) {
                if (seeCardMap.containsKey(n + 1 - cur)) {
                    seeCardMap.remove(cur);
                    seeCardMap.remove(n + 1 - cur);
                    coin -= 2;
                    maxRound += 1;
                    if (getSeeIdx(n, maxRound) < n) {
                        seeCardMap.put(cards[getSeeIdx(n, maxRound)], 0);
                        seeCardMap.put(cards[getSeeIdx(n, maxRound) - 1], 0);
                    } else {
                        allStop = true;
                    }
                    break;
                }
            }

            if (before == maxRound) break;
            if (allStop) break;
        }
        // 구현부 -- end


        return maxRound;
    }

    private int getSeeIdx(int n, int round) {
        return n / 3 + 2 * (round - 1) + 1;
    }
}