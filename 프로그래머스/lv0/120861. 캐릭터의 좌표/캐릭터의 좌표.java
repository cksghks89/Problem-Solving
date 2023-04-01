import java.util.HashMap;
import java.util.Map;

class Solution {
    static int N, M;

    public int[] solution(String[] keyinput, int[] board) {
        N = board[0] / 2;
        M = board[1] / 2;
        int[] answer = {0, 0};

        Map<String, int[]> map = new HashMap<>();
        map.put("up", new int[]{0, 1});
        map.put("down", new int[]{0, -1});
        map.put("left", new int[]{-1, 0});
        map.put("right", new int[]{1, 0});

        for (String key : keyinput) {
            int nx = answer[0] + map.get(key)[0];
            int ny = answer[1] + map.get(key)[1];

            if (isInRange(nx, ny)) {
                answer[0] = nx;
                answer[1] = ny;
            }
        }

        return answer;
    }

    private boolean isInRange(int x, int y) {
        return (-N <= x && x <= N) && (-M <= y && y <= M);
    }
}