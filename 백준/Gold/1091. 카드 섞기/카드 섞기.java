import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[] p;
    private static int[] s;
    private static int[] card;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        p = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        card = new int[N];
        for (int i = 0; i < N; i++) {
            card[i] = i;
        }

        int[] init = deal();

        int cnt = 0;
        while (true) {
            int[] deal = deal();

            if (cnt != 0 && isSameArr(init, deal)) {
                System.out.println(-1);
                return;
            }

            if (isSameArr(deal, p)) {
                break;
            }
            shuffle();
            cnt += 1;
        }

        System.out.println(cnt);
    }

    private static int[] deal() {
        int[] dealArr = new int[N];
        for (int i = 0; i < N; i++) {
            dealArr[card[i]] = i % 3;
        }
        return dealArr;
    }

    private static void shuffle() {
        int[] newCard = new int[N];
        for (int i = 0; i < N; i++) {
            newCard[s[i]] = card[i];
        }
        card = newCard;
    }

    private static boolean isSameArr(int[] arr1, int[] arr2) {
        for (int i = 0; i < N; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}
