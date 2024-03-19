import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] arr;
    private static int[] out;

    private static double answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];
        out = new int[3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        comb(0, 0);

        System.out.println(answer);
    }

    private static void comb(int start, int cnt) {
        if (cnt == 3) {
            double area = getArea(arr[out[0]], arr[out[1]], arr[out[2]]);
            answer = Math.max(answer, area);
            return;
        }

        for (int i = start; i < N; i++) {
            out[cnt] = i;
            comb(i + 1, cnt + 1);
        }
    }

    private static double getArea(int[] a, int[] b, int[] c) {
        // 신발끈 공식 사용하여 삼각형 넓이 구하기
        return Math.abs(((1.0 * a[0] * b[1] + b[0] * c[1] + c[0] * a[1])
                - (1.0 * b[0] * a[1] + c[0] * b[1] + a[0] * c[1]))) / 2.0;
    }
}
