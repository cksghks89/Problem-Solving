import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                list.add(new int[]{arr[i] + arr[j], i, j});
            }
        }

        list.sort((o1, o2) -> o1[0] - o2[0]);

        int result = 0;
        for (int i = 0; i < N; i++) {
            int p1 = 0;
            int p2 = list.size() - 1;

            while (p1 <= p2) {
                int mid = (p1 + p2) / 2;

                if (arr[i] < list.get(mid)[0]) {
                    p2 = mid - 1;
                } else if (arr[i] > list.get(mid)[0]) {
                    p1 = mid + 1;
                } else if (arr[i] == list.get(mid)[0]) {
                    int count = 0;
                    int left = mid;
                    while (left >= 0 && list.get(left)[0] == arr[i]) {
                        if (i != list.get(left)[1] && i != list.get(left)[2]) {
                            count += 1;
                            break;
                        }
                        left--;
                    }
                    int right = mid + 1;
                    while (right < list.size() && list.get(right)[0] == arr[i]) {
                        if (i != list.get(right)[1] && i != list.get(right)[2]) {
                            count += 1;
                            break;
                        }
                        right++;
                    }

                    if (count > 0) result++;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
