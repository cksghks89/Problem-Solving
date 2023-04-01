class Solution {
    public int solution(int n) {
        int answer = 0;
        int num = 1;
        while(num <= n) {
            answer += 1;
            num *= answer;
        }
        return answer - 1;
    }
}