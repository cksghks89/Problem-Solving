import java.util.*;

class Solution {
    TreeMap<String, Integer> map;

    public String[] solution(String[] orders, int[] course) {
        map = new TreeMap<>();

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

        // 주문 횟수 측정
        int[] cnt = new int[11];
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int len = entry.getKey().length();
            if (entry.getValue() < 2 || Arrays.binarySearch(course, len) < 0) {
                continue;
            }
            cnt[len] = Math.max(cnt[len], entry.getValue());
        }

        // 각각의 길이에서 가장 많은 주문횟수만 정답에 넣기
        List<String> answer = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (cnt[entry.getKey().length()] == entry.getValue()) {
                answer.add(entry.getKey());
            }
        }

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