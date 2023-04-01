import java.util.*;

class Solution {
    public int[] solution(int n) {
        Set<Integer> set = new HashSet<>();

        int d = 2;

        while (n >= 2) {
            if (n % d == 0) {
                set.add(d);
                n /= d;
                d = 2;
                continue;
            }
            d++;
        }

        return set.stream().mapToInt(Integer::valueOf).sorted().toArray();
    }
}