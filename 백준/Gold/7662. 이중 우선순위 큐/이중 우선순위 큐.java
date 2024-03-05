import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String op = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if (op.equals("I")) {
                    map.put(value, map.getOrDefault(value, 0) + 1);
                } else if (op.equals("D")) {
                    if(map.size() == 0){
                        continue;
                    }

                    if (value == 1) {
                        int maxKey = map.lastKey();
                        removeMap(map, maxKey);
                    } else if (value == -1) {
                        int minKey = map.firstKey();
                        removeMap(map, minKey);
                    }
                }
            }

            if(map.isEmpty()){
                System.out.println("EMPTY");
            }else{
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }
    }

    static void removeMap(TreeMap<Integer, Integer> map, int key) {
        if (map.get(key) == 1) {
            map.remove(key);
        } else if (map.get(key) > 1) {
            map.put(key, map.get(key) - 1);
        }
    }
}
