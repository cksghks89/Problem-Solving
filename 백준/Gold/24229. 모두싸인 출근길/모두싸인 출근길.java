import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board, (o1, o2) -> o1[0] - o2[0]);

        int start = board[0][0];
        int end = board[0][1];

        List<int[]> boardList = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            int[] cur = board[i];

            if (cur[0] <= end) {
                end = Math.max(end, cur[1]);
                continue;
            }
            boardList.add(new int[]{start, end});
            start = cur[0];
            end = cur[1];
        }
        boardList.add(new int[]{start, end});

        int maxLen = 0;
        int answer = 0;
        for (int i = 0; i < boardList.size(); i++) {
            int[] cur = boardList.get(i);

            if (maxLen >= cur[0]) {
                maxLen = Math.max(maxLen, 2 * cur[1] - cur[0]);
                answer = Math.max(answer, cur[1]);
            }
        }

        System.out.println(answer);
    }
}
