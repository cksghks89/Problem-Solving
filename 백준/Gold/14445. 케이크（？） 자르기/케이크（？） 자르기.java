import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long N = Long.parseLong(br.readLine());
        
        if (N == 1) {
            System.out.println(0);
        } else if (N % 2 == 0) {
            System.out.println(N / 2);
        } else {
            System.out.println(N / 2 + 1);
        }
    }
}