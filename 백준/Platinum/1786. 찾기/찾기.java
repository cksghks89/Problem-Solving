import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String T = br.readLine();
        String P = br.readLine();

        List<Integer> answer = kmp(T, P);

        System.out.println(answer.size());
        StringBuilder sb = new StringBuilder();
        for (int ans : answer) {
            sb.append(ans).append(' ');
        }
        System.out.println(sb);

    }

    static int[] getPi(String P) {
        int[] pi = new int[P.length()];
        int j = 0;
        for (int i = 1; i < P.length(); i++) {
            while (j > 0 && P.charAt(i) != P.charAt(j)) {
                j = pi[j - 1];
            }
            if (P.charAt(i) == P.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    static List<Integer> kmp(String T, String P) {
        List<Integer> rtn = new ArrayList<>();

        int[] pi = getPi(P);
        int j = 0;

        for (int i = 0; i < T.length(); i++) {
            while (j > 0 && T.charAt(i) != P.charAt(j)) {
                j = pi[j - 1];
            }
            if (T.charAt(i) == P.charAt(j)) {
                if (j + 1 == P.length()) {
                    rtn.add(i - j + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        return rtn;
    }
}
