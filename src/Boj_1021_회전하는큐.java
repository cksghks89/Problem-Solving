/*
    Boj 1021. 회전하는 큐
    level. silver 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Boj_1021_회전하는큐 {
    static List<Integer> nums;
    static int[] op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nums = IntStream.range(0, N)
                .boxed()
                .collect(Collectors.toList());
        op = Arrays.stream(br.readLine().split(" "))
                .mapToInt(x -> Integer.parseInt(x) - 1)
                .toArray();

        System.out.println(getMinExtractNum());
    }

    static int getMinExtractNum() {
        int minOp = 0;

        int idx = 0;

        for (int i = 0; i < op.length; i++) {
            int dest = op[i];

            minOp += getMinOp(idx, dest);
            nums.remove(dest);

            op = Arrays.stream(op).map(x -> {
                if(x > dest){
                    return x - 1;
                }else{
                    return x;
                }
            }).toArray();

            idx = dest;
        }
        return minOp;
    }

    static int getMinOp(int idx, int dest) {
        int right = (idx - dest + nums.size()) % nums.size();
        int left = (dest - idx + nums.size()) % nums.size();

        return Math.min(right, left);
    }
}
