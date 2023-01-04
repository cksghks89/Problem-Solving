/*
    Boj 2263. 트리의 순회
    level. gold 2
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2263_트리의순회 {
    static int[] inOrder;
    static int[] postOrder;
    static int[] inOrderIdxHash;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        postOrder = new int[n];
        inOrderIdxHash = new int[n + 1];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st1.nextToken());
            postOrder[i] = Integer.parseInt(st2.nextToken());
            inOrderIdxHash[inOrder[i]] = i;
        }

        getPreOrder(0, n - 1, 0, n - 1);
        System.out.println(sb.toString());
    }

    static void getPreOrder(int inOrderStart, int inOrderEnd, int postOrderStart, int postOrderEnd) {
        if(inOrderStart > inOrderEnd || postOrderStart > postOrderEnd){
            return;
        }

        int root = postOrder[postOrderEnd];
        sb.append(root).append(" ");

        int inOrderRootIdx = inOrderIdxHash[root];
        int leftCnt = inOrderRootIdx - inOrderStart;

        getPreOrder(inOrderStart, inOrderRootIdx - 1, postOrderStart, postOrderStart + leftCnt - 1);
        getPreOrder(inOrderRootIdx + 1, inOrderEnd, postOrderStart + leftCnt, postOrderEnd - 1);
    }
}
