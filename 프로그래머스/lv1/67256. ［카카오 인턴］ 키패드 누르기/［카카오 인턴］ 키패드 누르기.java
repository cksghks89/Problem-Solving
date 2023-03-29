class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuffer answer = new StringBuffer();

        int[][] loc = new int[128][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                loc[j + i * 3 + 1 + '0'][0] = i;
                loc[j + i * 3 + 1 + '0'][1] = j;
            }
        }
        loc['0'][0] = 3;
        loc['0'][1] = 1;
        loc['*'][0] = 3;
        loc['*'][1] = 0;
        loc['#'][0] = 3;
        loc['#'][1] = 2;

        int left = '*';
        int right = '#';

        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                answer.append('L');
                left = num + '0';
                continue;
            }

            if (num == 3 || num == 6 || num == 9) {
                answer.append('R');
                right = num + '0';
                continue;
            }

            int leftDist = Math.abs(loc[left][0] - loc[num + '0'][0]) + Math.abs(loc[left][1] - loc[num + '0'][1]);
            int rightDist = Math.abs(loc[right][0] - loc[num + '0'][0]) + Math.abs(loc[right][1] - loc[num + '0'][1]);

            if (leftDist < rightDist) {
                answer.append('L');
                left = num + '0';
            } else if (leftDist > rightDist) {
                answer.append('R');
                right = num + '0';
            } else if ("left".equals(hand)) {
                answer.append('L');
                left = num + '0';
            } else {
                answer.append('R');
                right = num + '0';
            }
        }
        return answer.toString();
    }
}