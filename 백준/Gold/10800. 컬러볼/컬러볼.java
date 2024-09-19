import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] input = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ci = Integer.parseInt(st.nextToken());
            int si = Integer.parseInt(st.nextToken());

            input[i][0] = i;
            input[i][1] = ci;
            input[i][2] = si;
        }

        Arrays.sort(input, (o1, o2) -> o1[2] - o2[2]);

        int[] sumColor = new int[N + 1];
        int[] answer = new int[N];

        List<int[]> temp = new ArrayList<>();
        int acc = 0;
        int size = 0;
        for (int i = 0; i < N; i++) {
            if (size == input[i][2]) {
                answer[input[i][0]] = acc - sumColor[input[i][1]];
            } else {
                for (int j = 0; j < temp.size(); j++) {
                    acc += temp.get(j)[2];
                    sumColor[temp.get(j)[1]] += temp.get(j)[2];
                }
                temp.clear();

                answer[input[i][0]] = acc - sumColor[input[i][1]];
                size = input[i][2];
            }
            temp.add(new int[]{input[i][0], input[i][1], input[i][2]});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append('\n');
        }

        System.out.println(sb);
    }
}