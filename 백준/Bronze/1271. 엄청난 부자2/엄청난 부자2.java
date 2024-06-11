import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String n = st.nextToken();
        String m = st.nextToken();

        BigInteger bin = new BigInteger(n);
        BigInteger bim = new BigInteger(m);

        System.out.println(bin.divide(bim).toString());
        System.out.println(bin.mod(bim).toString());
    }
}
