import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String timeStr = st.nextToken();
            int hour = Integer.parseInt(timeStr.split(":")[0]);
            int minute = hour * 60 + Integer.parseInt(timeStr.split(":")[1]);
            String name = st.nextToken();

            map.put(name, map.getOrDefault(name, 0) + minute);
        }

        List<Student> list = new ArrayList<>();
        for (String name : map.keySet()) {
            list.add(new Student(name, map.get(name)));
        }

        list.sort((o1, o2) -> {
            if (o1.cost != o2.cost) {
                return o2.cost - o1.cost;
            } else {
                return o1.name.compareTo(o2.name);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).name).append(' ').append(list.get(i).cost).append('\n');
        }

        System.out.println(sb);
    }

    private static class Student {
        String name;
        int cost;

        Student(String name, int time) {
            this.name = name;
            if (time <= 100) {
                this.cost = 10;
            } else {
                time -= 100;
                time = time / 50 + (time % 50 == 0 ? 0 : 1);
                this.cost = 10 + 3 * time;
            }
        }
    }
}
