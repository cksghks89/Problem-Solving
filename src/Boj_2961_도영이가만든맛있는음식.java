import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2961_도영이가만든맛있는음식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] inputs = new int[N][2];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            inputs[i][0] = Integer.parseInt(st.nextToken());
            inputs[i][1] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < (1 << N); i++) {
            int sin = 1, ssn = 0;

            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0) {
                    sin *= inputs[j][0];
                    ssn += inputs[j][1];
                }
            }

            min = Math.min(min, Math.abs(sin - ssn));
        }
        System.out.println(min);
    }
}
