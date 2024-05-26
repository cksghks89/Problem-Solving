import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        int answer = recursion(S, T);
        System.out.println(answer);
    }

    private static int recursion(String S, String T) {
        if (S.length() == T.length()) {
            if (S.equals(T)) return 1;
            return 0;
        }

        int result = 0;
        if (T.charAt(0) == 'B') {
            StringBuilder temp = new StringBuilder(T).reverse().deleteCharAt(T.length() - 1);
            result = recursion(S, temp.toString());
        }

        if (T.charAt(T.length() - 1) == 'A') {
            result += recursion(S, T.substring(0, T.length() - 1));
        }
        return result >= 1 ? 1 : 0;
    }
}
