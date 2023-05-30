class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        int answer = 0;
        if ("<".equals(ineq) && n < m) answer = 1;
        else if(">".equals(ineq) && n > m) answer = 1;
        
        if("=".equals(eq) && n == m) answer = 1;
        
        return answer;
    }
}