import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String str = "SciComLove";

        N %= 10;

        for (int i = 0; i < N; i++) {
            str = str.substring(1) + str.charAt(0);
        }

        System.out.println(str);
    }
}
