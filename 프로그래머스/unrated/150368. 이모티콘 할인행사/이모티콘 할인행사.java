import java.util.PriorityQueue;

class Solution {
    static int[][] users;
    static int[] emoticons;
    static int[] discount;
    static PriorityQueue<int[]> pq;

    public int[] solution(int[][] input1, int[] input2) {
        users = input1;
        emoticons = input2;
        discount = new int[emoticons.length];
        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) return o2[0] - o1[0];
            else return o2[1] - o1[1];
        });

        subSet(0);

        return pq.poll();
    }

    private void subSet(int cnt) {
        if (cnt == emoticons.length) {
            calc();
            return;
        }

        for (int i = 1; i <= 4; i++) {
            discount[cnt] = i * 10;
            subSet(cnt + 1);
        }
    }

    private void calc() {
        int[] money = new int[users.length];

        for (int i = 0; i < users.length; i++) {
            for (int j = 0; j < discount.length; j++) {
                if (users[i][0] <= discount[j]) {
                    money[i] += emoticons[j] * (100 - discount[j]) / 100;
                }
            }
        }
        int registCnt = 0;
        int total = 0;
        for (int i = 0; i < money.length; i++) {
            if (money[i] >= users[i][1]) registCnt++;
            else total += money[i];
        }

        pq.offer(new int[]{registCnt, total});
    }
}