import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    private static long[] l1, l2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        l1 = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        l2 = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        boolean cross = isCross();

        if (!cross) {
            System.out.println(0);
            return;
        }

        long mo = (l1[0] - l1[2]) * (l2[1] - l2[3]) - (l1[1] - l1[3]) * (l2[0] - l2[2]);

        if (mo == 0 && isSamePoint() == 1) {
            System.out.println(1);
            return;
        } else if (mo == 0 && isSamePoint() == 2) {
            System.out.println(1);
            System.out.println(getSamePoint());
            return;
        } else if (mo == 0 && isSamePoint() == 0) {
            System.out.println(0);
            return;
        }

        // x1y2 - y1x2, x3y4 - y3x4
        long m1 = (l1[0] * l1[3] - l1[1] * l1[2]);
        long m2 = (l2[0] * l2[3] - l2[1] * l2[2]);

        long x = m1 * (l2[0] - l2[2]) - (l1[0] - l1[2]) * m2;
        long y = m1 * (l2[1] - l2[3]) - (l1[1] - l1[3]) * m2;

        double px = x * 1.0 / mo;
        double py = y * 1.0 / mo;

        System.out.println(1);
        System.out.println(new BigDecimal(px) + " " + new BigDecimal(py));
    }

    private static int isSamePoint() {
        long x1 = l1[0];
        long y1 = l1[1];
        long x2 = l1[2];
        long y2 = l1[3];
        long x3 = l2[0];
        long y3 = l2[1];
        long x4 = l2[2];
        long y4 = l2[3];

        long pA, pB, pC, pD;
        if (x1 == x2) {
            pA = Math.min(y1, y2);
            pB = Math.max(y1, y2);
        } else {
            pA = Math.min(x1, x2);
            pB = Math.max(x1, x2);
        }
        if (x3 == x4) {
            pC = Math.min(y3, y4);
            pD = Math.max(y3, y4);
        } else {
            pC = Math.min(x3, x4);
            pD = Math.max(x3, x4);
        }

        if (pC < pB && pA < pD) {
            return 1;
        }
        if (pC == pB || pA == pD) {
            return 2;
        }

        return 0;
    }

    private static String getSamePoint() {
        long x1 = l1[0];
        long y1 = l1[1];
        long x2 = l1[2];
        long y2 = l1[3];
        long x3 = l2[0];
        long y3 = l2[1];
        long x4 = l2[2];
        long y4 = l2[3];

        if ((x1 == x3 && y1 == y3) || (x1 == x4 && y1 == y4)) return x1 + " " + y1;
        if ((x2 == x3 && y2 == y3) || (x2 == x4 && y2 == y4)) return x2 + " " + y2;
        return "";
    }

    private static boolean isCross() {
        int p123 = ccw(l1[0], l1[1], l1[2], l1[3], l2[0], l2[1]);
        int p124 = ccw(l1[0], l1[1], l1[2], l1[3], l2[2], l2[3]);
        int p341 = ccw(l2[0], l2[1], l2[2], l2[3], l1[0], l1[1]);
        int p342 = ccw(l2[0], l2[1], l2[2], l2[3], l1[2], l1[3]);

        long l1Min = Math.min(l1[0], l1[2]);
        long l1Max = Math.max(l1[0], l1[2]);
        long l2Min = Math.min(l2[0], l2[2]);
        long l2Max = Math.max(l2[0], l2[2]);

        if (p123 * p124 <= 0 && p341 * p342 <= 0) {
            if ((l1Min <= l2Min && l2Min <= l1Max) || (l2Min <= l1Min && l1Min <= l2Max)) {
                return true;
            }
        }

        return false;
    }

    private static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long ccw = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);

        if (ccw < 0) {
            return -1;
        } else if (ccw == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
