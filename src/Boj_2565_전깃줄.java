/*
    Boj 2565. 전깃줄
    level. gold 5
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_2565_전깃줄 {
    static int[][] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        line = new int[N][2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(line, (x, y) -> x[0] - y[0]);

        int[] arr = Arrays.stream(line).mapToInt((x) -> x[1]).toArray();

        int answer = N - lis(arr);
        System.out.println(answer);
    }

    static int lis(int[] arr) {
        ArrayList<Integer> lisList = new ArrayList<>();
        lisList.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];

            if (lisList.get(lisList.size() - 1) < cur) {
                lisList.add(cur);
            } else {
                int idx = Collections.binarySearch(lisList, cur);
                lisList.set(-idx - 1, cur);
            }
        }
        return lisList.size();
    }
}
