import java.util.*;

class Solution {
    private Map<String, Integer> friendMap;
    private int[][] giftMap;
    private int[] giftIndex;

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        // 초기화 ---- start
        giftMap = new int[friends.length][friends.length];  // 특정 대상에게 선물한 개수
        giftIndex = new int[friends.length];    // 선물 지수
        friendMap = new HashMap<>();    // 내가 선물한 총 개수 저장

        for (int i = 0; i < friends.length; i++) {
            friendMap.put(friends[i], i);
        }

        for (String gift : gifts) {
            String[] parse = gift.split(" ");
            giftMap[friendMap.get(parse[0])][friendMap.get(parse[1])] += 1;
        }

        for (int i = 0; i < giftIndex.length; i++) {
            giftIndex[i] = getGiftIdx(i);
        }
        // 초기화 ---- end


        // 구현부 --- start
        // 완전탐색
        for (int i = 0; i < friends.length; i++) {
            int giftCnt = 0;
            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue;

                // 1번 경우의 수. 선물을 주고받았고 더 많이 줬을 때
                if (giftMap[i][j] > giftMap[j][i]) {
                    giftCnt += 1;
                    continue;
                }

                // 2번 경우의 수. 선물 지수를 비교
                if (giftMap[i][j] == giftMap[j][i] && giftIndex[i] > giftIndex[j]) {
                    giftCnt += 1;
                }
            }

            // 정답 갱신
            answer = Math.max(answer, giftCnt);
        }
        // 구현부 ---- end

        return answer;
    }


    // 선물 지수 계산 모듈
    private int getGiftIdx(int x) {
        int giveCnt = 0;
        int receiveCnt = 0;

        for (int i = 0; i < giftMap.length; i++) {
            giveCnt += giftMap[x][i];
            receiveCnt += giftMap[i][x];
        }

        return giveCnt - receiveCnt;
    }
}