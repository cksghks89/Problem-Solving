import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int l = getPi(str);
        System.out.println(L - l);
    }

    static int getPi(String str) {
        int[] pi = new int[str.length()];
        int j = 0;
        for (int i = 1; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = pi[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi[str.length() - 1];
    }
}
