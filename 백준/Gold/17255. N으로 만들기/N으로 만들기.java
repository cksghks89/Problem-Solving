import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static String N;
    private static int len;
    private static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        len = N.length() * (N.length() + 1) / 2;

        for (int i = 0; i < N.length(); i++) {
            dfs(i, i, N.charAt(i) + "", N.charAt(i) + "");
        }

        System.out.println(set.size());
    }

    private static void dfs(int l, int r, String s, String total) {
        if (total.length() == len) {
            set.add(total);
            return;
        }

        if (l != 0) {
            char cur = N.charAt(l - 1);
            dfs(l - 1, r, cur + s, total + cur + s);
        }

        if (r != N.length() - 1) {
            char cur = N.charAt(r + 1);
            dfs(l, r + 1, s + cur, total + s + cur);
        }
    }
}
