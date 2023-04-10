import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("int");

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N / 4; i++) {
            sb.insert(0, "long ");
        }
        System.out.println(sb);
    }
}
