import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String jinju = "jinju";

        int cnt = 0;
        int cost = 0;

        int[] costArr = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String city = st.nextToken();
            int curCost = Integer.parseInt(st.nextToken());
            costArr[i] = curCost;

            if (city.equals(jinju)) cost = curCost;
        }

        for (int i = 0; i < N; i++) {
            cnt += costArr[i] > cost ? 1 : 0;
        }

        System.out.println(cost);
        System.out.println(cnt);

    }
}
