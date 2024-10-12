import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static long hiInit, lowInit;
    private static long[] hiBit, lowBit;
    private static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        hiBit = new long[m];
        lowBit = new long[m];

        list = new List[2 * n + 1];

        String initStr = br.readLine();
        for (int i = n - 1; i >= 0; i--) {
            if (initStr.charAt(i) == '0') continue;
            if (i >= 50) {
                hiInit = hiInit | (1L << (i - 50));
            } else {
                lowInit = lowInit | (1L << i);
            }
        }

        for (int i = 0; i < m; i++) {
            String cur = br.readLine();
            for (int j = n - 1; j >= 0; j--) {
                if (cur.charAt(j) == '0') continue;
                if (j >= 50) {
                    hiBit[i] = hiBit[i] | (1L << (j - 50));
                } else {
                    lowBit[i] = lowBit[i] | (1L << j);
                }
            }
        }

        subSet(0, hiInit, lowInit, new ArrayList<>());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2 * n + 1; i++) {
            if (list[i] == null) {
                sb.append(-1).append('\n');
            } else {
                sb.append(list[i].size()).append(' ');
                for (int btn : list[i]) sb.append(btn + 1).append(' ');
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }

    private static void subSet(int cnt, long hi, long low, List<Integer> numList) {
        if (cnt == m) {
            int count = 0;
            for (int i = 0; i < Math.min(50, n); i++) {
                if ((low & (1L << i)) == 0) {
                    count -= 1;
                } else {
                    count += 1;
                }
            }
            for (int i = 0; i < n - 50; i++) {
                if ((hi & (1L << i)) == 0) {
                    count -= 1;
                } else {
                    count += 1;
                }
            }

            if (list[count + n] == null) {
                list[count + n] = new ArrayList<>(numList);
            }
            return;
        }

        subSet(cnt + 1, hi, low, numList);
        numList.add(cnt);
        subSet(cnt + 1, hi ^ hiBit[cnt], low ^ lowBit[cnt], numList);
        numList.remove(numList.size() - 1);
    }
}
