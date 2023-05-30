import java.util.*;

class Solution {
    public int solution(int[] num_list) {
        int answer = 0;
        
        int mul = 1;
        int sum = Arrays.stream(num_list).sum();
        
        for (int i = 0; i < num_list.length; i++) {
            mul *= num_list[i];
        }
        
        if (mul < sum * sum) answer = 1;
        
        return answer;
    }
}