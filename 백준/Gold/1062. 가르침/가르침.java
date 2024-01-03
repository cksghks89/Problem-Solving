import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, K;
    private static char[] out;
    private static List<Set<Character>> words;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        // anta tica
        // a n t i c 5개는 필수
        // 26개의 알파벳 중 나머지 21개 중에서 K개를 배우는 경우의 수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        }

        words = new ArrayList<>();
        out = new char[K - 5];

        for (int i = 0; i < N; i++) {
            words.add(new HashSet<>());

            String word = br.readLine();
            for (char cur : word.toCharArray()) {
                words.get(i).add(cur);
            }
        }

        comb(0, 0);

        System.out.println(answer);
    }

    private static void comb(int cnt, int start) {
        if (cnt >= K - 5) {
            answer = Math.max(answer, counting());
            return;
        }

        for (int i = start; i < 26; i++) {
            if (basicWord((char) ('a' + i))) {
                continue;
            }
            out[cnt] = (char) ('a' + i);
            comb(cnt + 1, i + 1);
        }
    }

    private static boolean basicWord(char word) {
        return word == 'a' || word == 'n' || word == 't' || word == 'i' || word == 'c';
    }

    private static int counting() {
        int count = 0;
        Set<Character> outSet = new HashSet<>();
        for (char cur : out) outSet.add(cur);

        for (Set<Character> word : words) {
            boolean isCorrect = true;
            for (Character cur : word) {
                if (basicWord(cur)) {
                    continue;
                } else if (!outSet.contains(cur)) {
                    isCorrect = false;
                    break;
                }
            }

            if (isCorrect) count += 1;
        }

        return count;
    }
}
