import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    private static int A, B, C;
    private static boolean[][][] dp;
    private static TreeSet<Integer> capacityTreeSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dp = new boolean[A + 1][B + 1][C + 1];
        capacityTreeSet = new TreeSet<>();

        recursion(0, 0, C);

        StringBuilder sb = new StringBuilder();
        while (!capacityTreeSet.isEmpty()) {
            int capacity = capacityTreeSet.pollFirst();
            sb.append(capacity).append(' ');
        }

        System.out.println(sb);
    }

    private static void recursion(int a, int b, int c) {
        if (dp[a][b][c]) return;
        dp[a][b][c] = true;
        if (a == 0) capacityTreeSet.add(c);

        if (a != 0) {
            recursion(Math.max(a + b - B, 0), Math.min(B, a + b), c);   // a -> b
            recursion(Math.max(a + c - C, 0), b, Math.min(C, a + c));   // a -> c
        }

        if (b != 0) {
            recursion(Math.min(a + b, A), Math.max(a + b - A, 0), c);   // b -> a
            recursion(a, Math.max(a + c - C, 0), Math.min(b + c, C));   // b -> c
        }

        if (c != 0) {
            recursion(Math.min(A, a + c), b, Math.max(a + c - A, 0));   // c -> a
            recursion(a, Math.min(B, b + c), Math.max(b + c - B, 0));   // c -> b
        }
    }
}
