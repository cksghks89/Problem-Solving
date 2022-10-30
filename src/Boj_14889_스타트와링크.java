/*
    Boj 14889. 스타트와 링크
    level. silver 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_14889_스타트와링크 {
    static int N;
    static int[][] S;
    static boolean[] visited;
    static int[] team;
    static ArrayList<Integer> scoreList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        team = new int[N / 2];

        combination(0, N, N / 2, 0);

        System.out.println(getMinDiff());
    }

    static void combination(int depth, int n, int r, int s) {
        if (depth == r) {
            getScore(n);
            return;
        }

        for (int i = s; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                team[depth] = i;
                combination(depth + 1, n, r, i + 1);
                visited[i] = false;
            }
        }
    }

    static void getScore(int n) {
        int ourScore = 0;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (i == j) {
                    continue;
                }

                ourScore += S[team[i]][team[j]];
            }
        }
        scoreList.add(ourScore);
    }

    static int getMinDiff(){
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < scoreList.size() / 2; i++){
            min = Math.min(min, Math.abs(scoreList.get(i) - scoreList.get(scoreList.size() - i - 1)));
        }
        return min;
    }
}
