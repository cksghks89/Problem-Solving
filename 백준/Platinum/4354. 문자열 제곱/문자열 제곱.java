import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String t = br.readLine();
            if (".".equals(t)) break;

            int[] pi = getPi(t);

            int recur = t.length() - pi[t.length() - 1];
            if (recur + pi[t.length() - 1] == t.length() && t.length() % recur == 0) {
                System.out.println(t.length() / recur);
            } else {
                System.out.println(1);
            }
        }
    }

    static int[] getPi(String s) {
        int[] pi = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
