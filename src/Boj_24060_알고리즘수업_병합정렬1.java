/*
    Boj 24060. 알고리즘 수업 - 병합 정렬 1
    level. silver 4
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_24060_알고리즘수업_병합정렬1 {
    static int[] temp;
    static int K;
    static int cnt = 0;
    static int ans = -1;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        temp = new int[N];

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(A, 0, A.length-1);
        System.out.println(ans);
    }
    static void mergeSort(int[] A, int p, int r){
        if(p < r){
            int q = (p + r) / 2;
            mergeSort(A, p, q);
            mergeSort(A, q+1, r);
            merge(A, p, q, r);
        }
    }

    static void merge(int[] A, int p, int q, int r){
        int i = p;
        int j = q+1;
        int t = 0;

        while(i <= q && j <= r){
            if(A[i] <= A[j]){
                cnt++;
                if(cnt == K){
                    ans = A[i];
                }
                temp[t++] = A[i++];
            }else{
                cnt++;
                if(cnt == K){
                    ans = A[j];
                }
                temp[t++] = A[j++];
            }
        }
        while(i <= q){
            cnt++;
            if(cnt == K){
                ans = A[i];
            }
            temp[t++] = A[i++];
        }
        while(j <= r){
            cnt++;
            if(cnt == K){
                ans = A[j];
            }
            temp[t++] = A[j++];
        }

        i = p;
        t = 0;
        while(i <= r){
            A[i++] = temp[t++];
        }
    }
}
