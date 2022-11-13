/*
    Boj 18258. 큐2
    level. silver 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_18258_큐2 {
    static List<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        CustomQueue queue = new CustomQueue();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            if(op.equals("push")){
                queue.operation(op, Integer.parseInt(st.nextToken()));
            }else{
                sb.append(queue.operation(op, 0)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

}

class CustomQueue {
    List<Integer> queue = new LinkedList<>();

    public int operation(String op, int num) {
        switch (op) {
            case "push":
                this.push(num);
                break;
            case "pop":
                return this.pop();
            case "size":
                return this.size();
            case "empty":
                return this.empty();
            case "front":
                return this.front();
            case "back":
                return this.back();
        }

        return 0;
    }

    public void push(int num) {
        queue.add(num);
    }

    public int pop() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.remove(0);
    }

    public int size() {
        return queue.size();
    }

    public int empty() {
        if (queue.isEmpty()) {
            return 1;
        }
        return 0;
    }

    public int front() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.get(0);
    }

    public int back() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.get(queue.size() - 1);
    }
}
