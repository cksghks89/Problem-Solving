class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // 2n^2, 2n-1 (1부터 n까지)
        int sigma = (n/2) * (n/2+1);
        if (n % 2 == 0) {
            for (int i = 2; i <= n; i += 2) {
                answer += i * i;
            }
        } else answer = sigma - n/2 + n;
        
        
        return answer;
    }
}