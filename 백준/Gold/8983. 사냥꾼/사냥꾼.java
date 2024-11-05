import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int M, N, L;
    private static int[] guns;
    private static int[][] animals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        guns = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        animals = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            animals[i] = new int[]{x, y};
        }

        Arrays.sort(guns);

        int count = 0;

        for (int i = 0; i < N; i++) {
            int s = 0;
            int e = M - 1;

            while (s <= e) {
                int mid = (s + e) / 2;

                int distance = Math.abs(animals[i][0] - guns[mid]) + animals[i][1];

                if (distance <= L) {
                    count += 1;
                    break;
                }

                if (animals[i][0] < guns[mid]) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            }
        }

        System.out.println(count);
    }
}
