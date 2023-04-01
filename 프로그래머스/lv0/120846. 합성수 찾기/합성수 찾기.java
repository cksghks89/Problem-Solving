class Solution {
    public int solution(int n) {
        boolean[] isNotPrime = new boolean[n + 1];
        int answer = 1;
        
        for(int i = 2; i <= n; i++){
            if(!isNotPrime[i]){
                answer += 1;
                for(int j = i + i; j <= n; j += i){
                    isNotPrime[j] = true;
                }
            }
        }
        
        return n - answer;
    }
}