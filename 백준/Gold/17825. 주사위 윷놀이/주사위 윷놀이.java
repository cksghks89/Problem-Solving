import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static class Horse {
        int location;
        int path;
        boolean end;

        public Horse(int location, int path, boolean end) {
            this.location = location;
            this.path = path;
            this.end = end;
        }

        public Horse copy() {
            return new Horse(this.location, this.path, this.end);
        }
    }

    private static int[] dices;
    private static int[][] path;
    private static Horse[] horses;

    private static List<int[][]> sameList = new ArrayList<>();

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dices = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        path = new int[4][];
        path[0] = new int[21];
        for (int i = 0; i <= 20; i++) {
            path[0][i] = 2 * i;
        }
        path[1] = new int[]{0, 2, 4, 6, 8, 10, 13, 16, 19, 25, 30, 35, 40};
        path[2] = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 25, 30, 35, 40};
        path[3] = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 28, 27, 26, 25, 30, 35, 40};

        sameList.add(new int[][]{{0, 5}, {1, 5}});
        sameList.add(new int[][]{{0, 10}, {2, 10}});
        sameList.add(new int[][]{{0, 15}, {3, 15}});
        sameList.add(new int[][]{{0, 20}, {1, 12}, {2, 16}, {3, 22}});
        sameList.add(new int[][]{{1, 9}, {2, 13}, {3, 19}});
        sameList.add(new int[][]{{1, 10}, {2, 14}, {3, 20}});
        sameList.add(new int[][]{{1, 11}, {2, 15}, {3, 21}});

        horses = new Horse[4];
        for (int i = 0; i < 4; i++) {
            horses[i] = new Horse(0, 0, false);
        }

        subSet(0, 0);

        System.out.println(answer);
    }

    private static void subSet(int cnt, int score) {
        if (cnt == 10) {
            answer = Math.max(answer, score);
            return;
        }

        int curDice = dices[cnt];
        int endCount = 0;
        for (int i = 0; i < 4; i++) {
            Horse copy = horses[i].copy();

            if (horses[i].end) {
                endCount += 1;
                continue;
            }

            if (isMovePossible(horses[i].path, horses[i].location + curDice)) {
                move(horses[i], curDice);
                subSet(cnt + 1, horses[i].end ? score : score + path[horses[i].path][horses[i].location]);
            }

            horses[i] = copy;
        }

        if (endCount == 4) {
            answer = Math.max(answer, score);
            return;
        }
    }

    private static void move(Horse horse, int dice) {
        horse.location += dice;

        if (horse.path == 0) {
            if (horse.location == 5) horse.path = 1;
            else if (horse.location == 10) horse.path = 2;
            else if (horse.location == 15) horse.path = 3;
        }

        if (horse.location >= path[horse.path].length) {
            horse.end = true;
        }
    }

    private static boolean isMovePossible(int pathId, int location) {
        for (int i = 0; i < 4; i++) {
            if (horses[i].end) continue;
            if (pathId == horses[i].path && location == horses[i].location) {
                return false;
            }

            for (int j = 0; j < sameList.size(); j++) {
                boolean temp1 = false;
                boolean temp2 = false;
                for (int k = 0; k < sameList.get(j).length; k++) {
                    if (Arrays.equals(sameList.get(j)[k], new int[]{pathId, location})) {
                        temp1 = true;
                    }
                    if (Arrays.equals(sameList.get(j)[k], new int[]{horses[i].path, horses[i].location})) {
                        temp2 = true;
                    }
                }
                if (temp1 && temp2) return false;
            }
        }
        return true;
    }
}
