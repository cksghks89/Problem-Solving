import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] arr;

    private static int maxCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = new int[]{i, Integer.parseInt(st.nextToken())};

        for (int i = 0; i < N; i++) getCnt(i);

        System.out.println(maxCnt);
    }

    private static void getCnt(int id) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (i == id) continue;
            if (isView(id, i)) cnt += 1;
        }

        maxCnt = Math.max(maxCnt, cnt);
    }

    private static boolean isView(int id, int target) {
        int[] a = arr[Math.min(id, target)];
        int[] b = arr[Math.max(id, target)];

        for (int i = a[0] + 1; i < b[0]; i++) {
            double high = (1.0 * a[1] - b[1]) / (a[0] - b[0]) * (arr[i][0] - a[0]) + a[1];
            if (high <= arr[i][1]) return false;
        }
        return true;
    }
}
