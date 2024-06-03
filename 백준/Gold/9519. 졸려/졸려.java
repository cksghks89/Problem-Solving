import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        String str = br.readLine();
        char[] strCharArr = str.toCharArray();

        Map<String, Integer> map = new HashMap<>();

        map.put(str, 1);
        int count = 1;
        while (true) {
            count += 1;
            strCharArr = operation(strCharArr);
            String resultString = String.valueOf(strCharArr);

            if (count == X + 1) {
                System.out.println(resultString);
                return;
            }

            if (map.containsKey(resultString)) break;
            map.put(resultString, count);
        }

        X -= (map.get(String.valueOf(strCharArr)) - 1);
        X %= (count - map.get(String.valueOf(strCharArr)));

        for (int i = 0; i < X; i++) {
            strCharArr = operation(strCharArr);
        }

        System.out.println(String.valueOf(strCharArr));
    }

    private static char[] operation(char[] seq) {
        char[] newSeq = new char[seq.length];

        int idx = 0;
        for (int i = 0; i < seq.length; i += 2) {
            newSeq[idx++] = seq[i];
        }

        int lastStarted = seq.length % 2 == 0 ? seq.length - 1 : seq.length - 2;
        for (int i = 1; i < seq.length; i += 2, lastStarted -= 2) {
            newSeq[idx++] = seq[lastStarted];
        }

        return newSeq;
    }
}
