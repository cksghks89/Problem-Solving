import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] A = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int[] B = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int[] C = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        if ((A[0] == B[0] && B[0] == C[0]) || (A[1] == B[1] && B[1] == C[1]) || ccw(A, B, C)) {
            System.out.println(-1);
            return;
        }

        double AB = getDist(A, B);
        double BC = getDist(B, C);
        double CA = getDist(C, A);

        double centerA = (AB + CA) * 2;
        double centerB = (AB + BC) * 2;
        double centerC = (BC + CA) * 2;

        double max = Math.max(centerA, Math.max(centerB, centerC));
        double min = Math.min(centerA, Math.min(centerB, centerC));

        System.out.println(max - min);
    }

    private static boolean ccw(int[] A, int[] B, int[] C) {
        int ccw = (A[0] * B[1] + B[0] * C[1] + C[0] * A[1]) - (A[1] * B[0] + B[1] * C[0] + C[1] * A[0]);

        if (ccw == 0) return true;
        else return false;
    }

    private static double getDist(int[] x, int[] y) {
        return Math.sqrt(Math.pow(x[0] - y[0], 2) + Math.pow(x[1] - y[1], 2));
    }
}
