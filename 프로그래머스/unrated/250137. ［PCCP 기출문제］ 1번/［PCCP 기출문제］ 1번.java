class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int curHealth = health;
        int continuous = 0;
        int lastTime = 0;
        
        for (int[] attack : attacks) {
            continuous = attack[0] - lastTime - 1;
            curHealth = Math.min(continuous * bandage[1] + 
                             (continuous / bandage[0]) * bandage[2] + curHealth, health); 
            
            curHealth -= attack[1];
            lastTime = attack[0];
            
            if (curHealth <= 0) {
                answer = -1;
                break;
            }
            answer = curHealth;
        }
        
        return answer;
    }
}