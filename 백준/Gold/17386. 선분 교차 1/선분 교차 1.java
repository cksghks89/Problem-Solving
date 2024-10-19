import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static long[] l1;
    private static long[] l2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        l1 = new long[4];
        l2 = new long[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        l1[0] = Integer.parseInt(st.nextToken());
        l1[1] = Integer.parseInt(st.nextToken());
        l1[2] = Integer.parseInt(st.nextToken());
        l1[3] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        l2[0] = Integer.parseInt(st.nextToken());
        l2[1] = Integer.parseInt(st.nextToken());
        l2[2] = Integer.parseInt(st.nextToken());
        l2[3] = Integer.parseInt(st.nextToken());

        // 1-2-3, 1-2-4
        int ccw123 = ccw(l1[0], l1[1], l1[2], l1[3], l2[0], l2[1]);
        int ccw124 = ccw(l1[0], l1[1], l1[2], l1[3], l2[2], l2[3]);

        int ccw341 = ccw(l2[0], l2[1], l2[2], l2[3], l1[0], l1[1]);
        int ccw342 = ccw(l2[0], l2[1], l2[2], l2[3], l1[2], l1[3]);


        if (ccw123 * ccw124 <= 0 && ccw341 * ccw342 <= 0) {
            long minL1X = Math.min(l1[0], l1[2]);
            long maxL1X = Math.max(l1[0], l1[2]);
            long minL2X = Math.min(l2[0], l2[2]);
            long maxL2X = Math.max(l2[0], l2[2]);

            if((minL1X <= minL2X && minL2X <= maxL1X) || (minL2X <= minL1X && minL1X <= maxL2X)) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);

    }

    private static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long result = (x1 * y2 + x2 * y3 + x3 * y1) - (y1 * x2 + y2 * x3 + y3 * x1);

        if (result < 0) {
            return -1;
        } else if (result == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
