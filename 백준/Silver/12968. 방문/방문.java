import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        if (K == 1) {
            System.out.println(1);
            return;
        }
        
        if (R % 2 == 1 && C % 2 == 1) {
            System.out.println(0);
        } else {
            System.out.println(1);
        }
    }
}