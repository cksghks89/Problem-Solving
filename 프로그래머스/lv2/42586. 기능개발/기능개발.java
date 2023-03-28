import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        // 개발 완료 날짜를 미리 계산
        int[] deploy = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            int remain = 100 - progresses[i];
            deploy[i] = remain / speeds[i] + (remain % speeds[i] == 0 ? 0 : 1);
        }

        Stack<Integer> stack = new Stack<>();

        // 스택을 이용하여 묶음 카운팅
        stack.push(deploy[0]);
        int cnt = 1;
        for (int i = 1; i < deploy.length; i++) {
            if (stack.peek() >= deploy[i]) {
                cnt++;
            } else {
                stack.push(deploy[i]);
                answer.add(cnt);
                cnt = 1;
            }
        }
        answer.add(cnt);

        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
}