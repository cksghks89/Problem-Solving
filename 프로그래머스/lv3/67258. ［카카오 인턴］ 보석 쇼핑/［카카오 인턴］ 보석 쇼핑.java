import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemCnt = new HashSet<>(Arrays.asList(gems));
        int n = gemCnt.size();

        Map<String, Integer> map = new HashMap<>();
        List<int[]> rtn = new ArrayList<>();

        int p1 = 0;
        int p2 = 0;

        while (p2 != gems.length || map.size() == n) {

            if (map.size() != n) {
                map.put(gems[p2], map.getOrDefault(gems[p2++], 0) + 1);
            } else {
                if (map.get(gems[p1]) == 1) {
                    map.remove(gems[p1]);
                } else {
                    map.put(gems[p1], map.get(gems[p1]) - 1);
                }
                p1++;
            }

            if (map.size() == n) {
                rtn.add(new int[]{p1 + 1, p2});
            }
        }

        rtn.sort((o1, o2) -> {
            int o1Len = o1[1] - o1[0];
            int o2Len = o2[1] - o2[0];
            if (o1Len != o2Len) {
                return o1Len - o2Len;
            } else {
                return o1[0] - o2[0];
            }
        });

        return rtn.get(0);
    }
}