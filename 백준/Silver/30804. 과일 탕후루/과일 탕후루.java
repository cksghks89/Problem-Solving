import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] fruit = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int p1 = 0;
        int p2 = 0;

        Map<Integer, Integer> fruitMap = new HashMap<>();
        int answer = 0;
        while (p2 != N) {
            if (fruitMap.size() < 2) {
                fruitMap.put(fruit[p2], fruitMap.getOrDefault(fruit[p2], 0) + 1);
                p2 += 1;
                answer = Math.max(answer, p2 - p1);
                continue;
            }

            if (fruitMap.containsKey(fruit[p2])) {
                fruitMap.put(fruit[p2], fruitMap.get(fruit[p2]) + 1);
                p2 += 1;
                answer = Math.max(answer, p2 - p1);
                continue;
            }

            while (fruitMap.size() >= 2) {
                fruitMap.put(fruit[p1], fruitMap.get(fruit[p1]) - 1);
                if (fruitMap.get(fruit[p1]) == 0) fruitMap.remove(fruit[p1]);
                p1 += 1;
            }
        }

        System.out.println(answer);
    }
}
