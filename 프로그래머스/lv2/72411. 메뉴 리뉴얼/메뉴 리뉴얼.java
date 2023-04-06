import java.util.*;

class Solution {
    Map<String, Integer> map;

    public String[] solution(String[] orders, int[] course) {
        map = new HashMap<>();

        // String[] -> char[][] and 정렬
        char[][] charOrders = new char[orders.length][];
        for (int i = 0; i < orders.length; i++) {
            charOrders[i] = new char[orders[i].length()];
            for (int j = 0; j < orders[i].length(); j++) {
                charOrders[i][j] = orders[i].charAt(j);
            }
            Arrays.sort(charOrders[i]);
        }

        // 조합으로 모든 경우의 수 체크
        for (int i = 0; i < charOrders.length; i++) {
            for (int n : course) {
                if (charOrders[i].length < n) break;
                comb(n, 0, 0, charOrders[i], "");
            }
        }

        // 각 조합에 대해 가장 높은 주문만 정답에 추가
        int[] cnt = new int[11];
        List<String> answer = new ArrayList<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        pq.addAll(map.entrySet());

        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> cur = pq.poll();
            if (cnt[cur.getKey().length()] <= cur.getValue() && cur.getValue() >= 2) {
                answer.add(cur.getKey());
                cnt[cur.getKey().length()] = cur.getValue();
            }
        }

        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }

    void comb(int n, int cnt, int start, char[] order, String menu) {
        if (cnt == n) {
            map.put(menu, map.getOrDefault(menu, 0) + 1);
            return;
        }

        for (int i = start; i < order.length; i++) {
            comb(n, cnt + 1, i + 1, order, menu + order[i]);
        }
    }
}