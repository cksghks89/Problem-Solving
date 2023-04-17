import java.util.Arrays;

class Solution {
    static int N;
    static int[] out;
    static int[] result;
    static int max;

    public int[] solution(int n, int[] info) {
        N = n;
        out = new int[11];
        result = new int[11];
        max = Integer.MIN_VALUE;

        perm(0, 0, info);

        if (max == Integer.MIN_VALUE) return new int[]{-1};
        return result;
    }

    private void perm(int cnt, int s, int[] info) {
        if (cnt == N) {
            calc(info);
            return;
        }

        for (int i = s; i < 11; i++) {
            out[i]++;
            perm(cnt + 1, i, info);
            out[i]--;
        }
    }

    private void calc(int[] info) {
        int uppeach = 0, ryan = 0;

        for (int i = 0; i < 10; i++) {
            if (out[i] > info[i]) ryan += 10 - i;
            else if (info[i] != 0) uppeach += 10 - i;
        }

        if (ryan <= uppeach) return;

        if (max < ryan - uppeach) {
            max = ryan - uppeach;
            result = Arrays.copyOf(out, 11);
        } else if (max == ryan - uppeach) {
            for (int i = 10; i >= 0; i--) {
                if (out[i] == result[i]) continue;
                if (out[i] < result[i]) break;
                if (out[i] > result[i]) {
                    result = Arrays.copyOf(out, 11);
                    break;
                }
            }
        }
    }
}