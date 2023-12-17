import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<int[]> calculateTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        calculateTable = new ArrayList<>();
        comb(16, 3, 0, 0, new int[3]);
        calculateTable.sort((o1, o2) -> o1[0] - o2[0]);

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] mbtis = new int[16];
            String[] inputs = br.readLine().split(" ");
            for (String input : inputs) {
                mbtis[convertMbtiToCode(input)] += 1;
            }

            for (int[] item : calculateTable) {
                mbtis[item[1]] -= 1;
                mbtis[item[2]] -= 1;
                mbtis[item[3]] -= 1;

                boolean isCorrect = true;
                for (int mbti : mbtis) {
                    if (mbti < 0) {
                        isCorrect = false;
                        break;
                    }
                }
                if (isCorrect) {
                    sb.append(item[0]).append('\n');
                    break;
                } else {
                    mbtis[item[1]] += 1;
                    mbtis[item[2]] += 1;
                    mbtis[item[3]] += 1;
                }
            }
        }

        System.out.println(sb);
    }

    private static void comb(int n, int r, int cnt, int start, int[] out) {
        if (cnt == r) {
            int sumOfDistance = getDistance(out[0], out[1]) + getDistance(out[1], out[2]) + getDistance(out[2], out[0]);
            calculateTable.add(new int[]{sumOfDistance, out[0], out[1], out[2]});
            return;
        }

        for (int i = start; i < n; i++) {
            out[cnt] = i;
            comb(n, r, cnt + 1, i, out);
        }
    }

    private static int getDistance(int a, int b) {
        int distance = 0;
        for (int i = 0; i < 4; i++) {
            int curA = a % 2;
            int curB = b % 2;

            if (curA != curB) {
                distance += 1;
            }

            a /= 2;
            b /= 2;
        }
        return distance;
    }

    private static int convertMbtiToCode(String mbti) {
        if (mbti.length() != 4) return 0;

        int code = 0;
        if (mbti.charAt(0) == 'E') code += (int) Math.pow(2, 3);
        if (mbti.charAt(1) == 'N') code += (int) Math.pow(2, 2);
        if (mbti.charAt(2) == 'F') code += (int) Math.pow(2, 1);
        if (mbti.charAt(3) == 'J') code += (int) Math.pow(2, 0);

        return code;
    }
}
