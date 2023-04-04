import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        Set<Integer> win = new HashSet<>();
        for (int n : win_nums) {
            win.add(n);
        }

        int zero = 0;
        int correct = 0;
        for (int i = 0; i < 6; i++) {
            if (lottos[i] == 0) zero++;
            else if (win.contains(lottos[i])) correct++;
        }

        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        return new int[]{rank[zero + correct], rank[correct]};
    }
}