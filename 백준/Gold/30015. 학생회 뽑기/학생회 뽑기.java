import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) list.add(Integer.parseInt(st.nextToken()));


        // 가장 높은 자리수부터 개수 체크하기
        int answer = 0;
        for (int i = 20; i >= 0; i--) {
            if (checkBit(1 << i)) {
                answer += 1 << i;
            }
        }

        System.out.println(answer);
    }

    private static boolean checkBit(int bit) {
        List<Integer> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);
            if ((cur & bit) > 0) newList.add(cur);
        }

        if (newList.size() < K) return false;
        list = newList;
        return true;
    }
}
