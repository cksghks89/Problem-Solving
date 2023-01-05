/*
    Boj 5639. 이진 검색 트리
    level. gold 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Boj_5639_이진검색트리 {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "";
        LinkedList<Integer> input = new LinkedList<>();

        while((str = br.readLine()) != null && !str.isEmpty()) {
            input.add(Integer.parseInt(str));
        }

        sb = new StringBuilder();
        postOrder(input);
        System.out.print(sb.toString());
    }

    static void postOrder(LinkedList<Integer> preOrder) {
        if(preOrder.isEmpty()) {
            return;
        }

        int root = preOrder.poll();

        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();

        for(int num : preOrder) {
            if(root > num) {
                left.add(num);
            }else {
                right.add(num);
            }
        }

        postOrder(left);
        postOrder(right);
        sb.append(root).append("\n");
    }
}
