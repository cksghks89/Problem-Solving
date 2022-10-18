/*
    Boj 2477. 참외밭
    level. silver 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2477_참외밭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        StringTokenizer st;

        ArrayList<ArrayList<Integer>> xy = new ArrayList<>();
        int x = 0;
        int y = 0;

        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            xy.add(new ArrayList<>(Arrays.asList(x, y)));

            int cur = Integer.parseInt(st.nextToken());
            if (str.equals("1")) {
                x += cur;
                maxX = Math.max(maxX, cur);
            } else if (str.equals("2")) {
                x -= cur;
                maxX = Math.max(maxX, cur);
            } else if (str.equals("3")) {
                y -= cur;
                maxY = Math.max(maxY, cur);
            } else if (str.equals("4")) {
                y += cur;
                maxY = Math.max(maxY, cur);
            }
        }

        ArrayList<ArrayList<Integer>> cloneXy = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            cloneXy.add((ArrayList<Integer>) xy.get(i).clone());
        }

        cloneXy.sort((a, b) -> a.get(0) - b.get(0));
        int[] mid = new int[2];
        mid[0] = cloneXy.get(2).get(0);
        cloneXy.sort((a, b) -> a.get(1) - b.get(1));
        mid[1] = cloneXy.get(2).get(1);

        int[] t1 = new int[2];
        int[] t2 = new int[2];
        for(int i = 0; i < 6; i++){
            if(mid[0] == xy.get(i).get(0) && mid[1] == xy.get(i).get(1)){
                if(i != 0 && i != 5){
                    t1 = xy.get(i-1).stream().mapToInt(Integer::valueOf).toArray();
                    t2 = xy.get(i+1).stream().mapToInt(Integer::valueOf).toArray();
                }else if(i == 0){
                    t1 = xy.get(1).stream().mapToInt(Integer::valueOf).toArray();
                    t2 = xy.get(5).stream().mapToInt(Integer::valueOf).toArray();
                }else{
                    t1 = xy.get(0).stream().mapToInt(Integer::valueOf).toArray();
                    t2 = xy.get(4).stream().mapToInt(Integer::valueOf).toArray();
                }
                break;
            }
        }

        int dx = Math.abs(mid[0] - t1[0]) + Math.abs(mid[1] - t1[1]);
        int dy = Math.abs(mid[0] - t2[0]) + Math.abs(mid[1] - t2[1]);

        System.out.println( (maxX * maxY - dx * dy) * K );
    }
}
