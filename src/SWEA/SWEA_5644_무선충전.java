import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5644_무선충전 {
    static int M, N;
    static int[][] bc;
    static int[] A;
    static int[] B;
    static int[] curA, curB;
    static int result;
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


            bc = new int[N][];
            for (int i = 0; i < N; i++) {
                bc[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            result = 0;
            start();
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb.toString());
    }

    static void start() {
        curA = new int[]{1, 1};
        curB = new int[]{10, 10};

        for (int i = 0; i < A.length; i++) {
            result += search();

            curA[0] += dx[A[i]];
            curA[1] += dy[A[i]];

            curB[0] += dx[B[i]];
            curB[1] += dy[B[i]];
        }
        result += search();
    }

    static int search() {
        ArrayList<Integer> searchA = new ArrayList<>();
        ArrayList<Integer> searchB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int distA = Math.abs(bc[i][0] - curA[0]) + Math.abs(bc[i][1] - curA[1]);
            int distB = Math.abs(bc[i][0] - curB[0]) + Math.abs(bc[i][1] - curB[1]);

            if (distA <= bc[i][2]) searchA.add(i);
            if (distB <= bc[i][2]) searchB.add(i);
        }
        if (searchA.isEmpty() && searchB.isEmpty()) {
            return 0;
        } else if (searchB.isEmpty()) {
            return searchA.stream().map(x -> bc[x][3]).mapToInt(Integer::valueOf).max().orElse(0);
        } else if (searchA.isEmpty()) {
            return searchB.stream().map(x -> bc[x][3]).mapToInt(Integer::valueOf).max().orElse(0);
        }

        int max = 0;
        for (int i = 0; i < searchA.size(); i++) {
            for (int j = 0; j < searchB.size(); j++) {
                if (searchA.get(i).equals(searchB.get(j))) {
                    max = Math.max(max, bc[searchA.get(i)][3]);
                } else {
                    max = Math.max(max, bc[searchA.get(i)][3] + bc[searchB.get(j)][3]);
                }
            }
        }
        return max;
    }
}
