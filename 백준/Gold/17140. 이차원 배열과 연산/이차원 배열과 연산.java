import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int r, c, k;
    private static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        A = new int[3][3];

        for (int i = 0; i < 3; i++) {
            A[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = -1;
        for (int i = 0; i <= 100; i++) {
            if (r < A.length && c < A[0].length && A[r][c] == k) {
                answer = i;
                break;
            }

            if (A.length >= A[0].length) {
                rOperation();
            } else {
                cOperation();
            }
        }

        System.out.println(answer);
    }

    private static void rOperation() {
        // 모든 행에 대한 정렬
        List<Integer>[] list = new List[A.length];
        for (int i = 0; i < A.length; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 0) continue;
                list[i].add(A[i][j]);
            }
        }

        List<int[]>[] sortedList = sortList(list);
        int maxLength = getMaxLength(sortedList);

        // 새로운 배열 생성
        int[][] newA = new int[A.length][Math.min(100, maxLength)];
        for (int i = 0; i < A.length; i++) {
            int idx = 0;
            try {
                for (int[] cur : sortedList[i]) {
                    newA[i][idx++] = cur[0];
                    newA[i][idx++] = cur[1];
                }
            } catch (Exception e) {
                continue;
            }
        }
        A = newA;
    }

    private static void cOperation() {
        // 모든 행에 대한 정렬
        List<Integer>[] list = new List[A[0].length];
        for (int i = 0; i < A[0].length; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < A[0].length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[j][i] == 0) continue;
                list[i].add(A[j][i]);
            }
        }

        List<int[]>[] sortedList = sortList(list);

        int maxLength = getMaxLength(sortedList);

        int[][] newA = new int[Math.min(100, maxLength)][A[0].length];
        for (int i = 0; i < A[0].length; i++) {
            int idx = 0;
            try {
                for (int[] cur : sortedList[i]) {
                    newA[idx++][i] = cur[0];
                    newA[idx++][i] = cur[1];
                }

            } catch (Exception e) {
                continue;
            }
        }

        A = newA;
    }

    private static int getMaxLength(List<int[]>[] sortedList) {
        int maxLen = -1;
        for (int i = 0; i < sortedList.length; i++) {
            maxLen = Math.max(maxLen, sortedList[i].size() * 2);
        }
        return maxLen;
    }

    private static List<int[]>[] sortList(List<Integer>[] list) {
        List<int[]>[] result = new List[list.length];
        for (int i = 0; i < list.length; i++) {
            result[i] = new ArrayList<>();
            list[i].sort((o1, o2) -> o1 - o2);
        }

        for (int i = 0; i < list.length; i++) {
            int prev = list[i].get(0);
            int count = 0;

            for (int j = 0; j < list[i].size(); j++) {
                if (prev == list[i].get(j)) {
                    count += 1;
                    if (j == list[i].size() - 1) {
                        result[i].add(new int[]{prev, count});
                    }
                } else {
                    result[i].add(new int[]{prev, count});
                    count = 1;
                    prev = list[i].get(j);
                    if (j == list[i].size() - 1) {
                        result[i].add(new int[]{prev, count});
                    }
                }
            }
        }

        for (int i = 0; i < list.length; i++) {
            result[i].sort((o1, o2) -> {
                if (o1[1] != o2[1]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            });
        }

        return result;
    }
}
