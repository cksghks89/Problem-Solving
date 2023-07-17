class Solution {
    static long x, y;
    
    public long solution(int r1, int r2) {
        Long r1Count = 0L;
        Long r2Count = 0L;
        
        x = 0;
        y = r1;
        for (int i = r1; i >= 1; i--) {
            r1Count += getR1Count(r1);
            y--;
        }
        
        x = 0;
        y = r2;
        for (int i = r2; i >= 1; i--) {
            r2Count += getR2Count(r2);
            y--;
        }
        
        r1Count *= 2;
        r2Count *= 2;
        
        r1Count += r1 * 2 + 1 - 2;
        r2Count += r2 * 2 + 1;
        
        System.out.println(r1Count + " " + r2Count);
        
        return r2Count - r1Count;
    }
    
    private long getR1Count(int r) {
        while (x*x + y*y < 1L * r * r) x += 1;
        return x == 0 ? 0 : (x - 1) * 2 + 1;
    }
    
    private long getR2Count(int r) {
        while (x*x + y*y <= 1L * r * r) x += 1;
        return (x - 1) * 2 + 1;
    }
}