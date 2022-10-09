/*
    Boj 9663. N-Queen
    level. gold 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_9663_NQueen {
    static int N;
    static int[] board;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N];

        nQueen(0);
        System.out.println(cnt);
    }

    public static int nQueen(int row){
        if(row == N){
            cnt++;
            return cnt;
        }

        for(int i = 0; i < N; i++){
            board[row] = i;

            if(isPromising(row)){
                nQueen(row + 1);
            }
        }
        return cnt;
    }

    public static boolean isPromising(int row){
        for(int i = 0; i < row; i++){
            if(board[row] == board[i] || row - i == Math.abs(board[row] - board[i])){
                return false;
            }
        }
        return true;
    }
}
