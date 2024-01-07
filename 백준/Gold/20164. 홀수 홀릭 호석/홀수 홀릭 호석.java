import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Main {

    private static TreeSet<Integer> treeSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        treeSet = new TreeSet<>();

        recursion(N, 0);

        System.out.println(treeSet.first() + " " + treeSet.last());
    }

    private static void recursion(int num, int oddCnt) {
        // 1. 각 자리의 홀수 개수 세기
        oddCnt += getOddCnt(num);

        // 2. 숫자가 한자리 일때
        if (num < 10) {
            treeSet.add(oddCnt);
            return;
        }

        // 3. 숫자가 두자리 일때
        if (num < 100) {
            recursion(num % 10 + num / 10, oddCnt);
            return;
        }

        // 4. 숫자가 3자리 이상일때
        List<Integer> list = new ArrayList<>();
        comb(0, 0, String.valueOf(num).length() - 1, new int[2], String.valueOf(num), list);

        for (Integer cur : list) {
            recursion(cur, oddCnt);
        }
    }

    private static void comb(int cnt, int start, int n, int[] out, String strNum, List<Integer> list) {
        if (cnt == 2) {
            int num1 = Integer.parseInt(strNum.substring(0, out[0] + 1));
            int num2 = Integer.parseInt(strNum.substring(out[0] + 1, out[1] + 1));
            int num3 = Integer.parseInt(strNum.substring(out[1] + 1));

            list.add(num1 + num2 + num3);
            return;
        }

        for (int i = start; i < n; i++) {
            out[cnt] = i;
            comb(cnt + 1, i + 1, n, out, strNum, list);
        }
    }

    private static int getOddCnt(int num) {
        int oddCnt = 0;
        while (num != 0) {
            if ((num % 10) % 2 == 1) {
                oddCnt += 1;
            }
            num /= 10;
        }
        return oddCnt;
    }
}
