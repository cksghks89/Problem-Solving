import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BitSet bitSet = new BitSet(1 << 25);

        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            int cur = Integer.parseInt(st.nextToken());

            if (bitSet.get(cur)) continue;
            bitSet.set(cur);
            sb.append(cur).append(' ');
        }

        System.out.println(sb);
    }
}
