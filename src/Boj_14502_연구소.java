/*
    Boj 14502. 연구소
    level. gold 4
    solved by 송찬환
 */
import java.util.*;

public class Boj_14502_연구소 {
    public static int cnt = 0;
    public static void combination(int n, int r, int depth, boolean[] visit, ArrayList<int[]> zero, int[][] lab){
        if(r == 0){
            for(int i = 0; i < visit.length; i++){
                if(visit[i]){
                    lab[zero.get(i)[0]][zero.get(i)[1]] = 1;
                }
            }

            cnt = Math.max(BFS(lab), cnt);

            for(int i = 0; i < visit.length; i++){
                if(visit[i]){
                    lab[zero.get(i)[0]][zero.get(i)[1]] = 0;
                }
            }
            return;
        }
        if(depth == n){
            return;
        }

        visit[depth] = true;
        combination(n, r-1, depth+1, visit, zero, lab);
        visit[depth] = false;
        combination(n, r, depth+1, visit, zero, lab);
    }
    public static int BFS(int[][] lab){
        boolean[][] visit = new boolean[lab.length][lab[0].length];
        int[][] labClone = new int[lab.length][lab[0].length];
        for(int i = 0; i < lab.length; i++){
            System.arraycopy(lab[i], 0, labClone[i], 0, lab[i].length);
        }

        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < labClone.length; i++){
            for(int j = 0; j < labClone[i].length; j++){
                if(labClone[i][j] == 2){
                    queue.add(new int[]{i, j});
                }
            }
        }

        while(!queue.isEmpty()){
            int[] nowPoint = queue.poll();

            if(visit[nowPoint[0]][nowPoint[1]]){
                continue;
            }
            visit[nowPoint[0]][nowPoint[1]] = true;
            labClone[nowPoint[0]][nowPoint[1]] = 2;

            int[] x = {0, 0, 1, -1};
            int[] y = {1, -1, 0, 0};
            for(int i = 0; i < 4; i++){
                if(0 <= nowPoint[0] + y[i] && nowPoint[0] + y[i] < labClone.length){
                    if(0 <= nowPoint[1] + x[i] && nowPoint[1] + x[i] < labClone[0].length){
                        if(labClone[nowPoint[0]+y[i]][nowPoint[1]+x[i]] == 0){
                            queue.add(new int[]{nowPoint[0]+y[i], nowPoint[1]+x[i]});
                        }
                    }
                }
            }
        }
        int count = 0;
        for(int[] x: labClone){
            for(int y: x){
                if(y == 0){
                    count += 1;
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String[] temp = sc.nextLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        int[][] lab = new int[N][];
        for(int i = 0; i < N; i++){
            lab[i] = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        ArrayList<int[]> zero = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(lab[i][j] == 0){
                    zero.add(new int[]{i, j});
                }
            }
        }
        boolean[] visit = new boolean[zero.size()];
        combination(zero.size(), 3, 0, visit, zero, lab);
        System.out.println(cnt);
    }
}
