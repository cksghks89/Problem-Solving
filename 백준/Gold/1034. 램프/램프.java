import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static boolean[][] ramp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ramp = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == '1') {
                    ramp[i][j] = true;
                }
            }
        }

        int K = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            List<Integer> turnOffIndexList = getTurnOffIndexList(i);
            if (turnOffIndexList.size() > K || turnOffIndexList.size() % 2 != K % 2) continue;

            doSwitch(turnOffIndexList);
            int countOfTurnOn = getCountOfTurnOn();
            answer = Math.max(answer, countOfTurnOn);
            doSwitch(turnOffIndexList);
        }

        System.out.println(answer);
    }

    private static int getCountOfTurnOn() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            count += 1;
            for (int j = 0; j < M; j++) {
                if (!ramp[i][j]) {
                    count -= 1;
                    break;
                }
            }
        }
        return count;
    }

    private static void doSwitch(List<Integer> turnOffIndexList) {
        for (int index : turnOffIndexList) {
            for (int i = 0; i < N; i++) {
                ramp[i][index] = !ramp[i][index];
            }
        }
    }
    
    private static List<Integer> getTurnOffIndexList(int row) {
        List<Integer> idxList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            if (!ramp[row][i]) {
                idxList.add(i);
            }
        }
        return idxList;
    }
}
