/*
    Boj 5373. 큐빙
    level. platinum 5
    solved by 송찬환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_5373_큐빙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            Cube cube = new Cube();

            int n = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                char op = input[i].charAt(0);
                char turn = input[i].charAt(1);

                cube.cubing(op, turn);
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(cube.U[i][j]);
                }
                sb.append('\n');
            }
        }

        System.out.print(sb.toString());
    }

    static class Cube {
        char[][] F = {{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}};
        char[][] B = {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}};
        char[][] L = {{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}};
        char[][] R = {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}};
        char[][] U = {{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}};
        char[][] D = {{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}};

        public void cubing(char op, char turn) {
            // 원하는 면이 Front 로 오도록 큐브 자체를 회전
            switch (op) {
                case 'F':
                    break;
                case 'B':
                    leftToFrontTurn();
                    leftToFrontTurn();
                    break;
                case 'L':
                    leftToFrontTurn();
                    break;
                case 'R':
                    leftToFrontTurn();
                    leftToFrontTurn();
                    leftToFrontTurn();
                    break;
                case 'U':
                    if (turn == '+') {
                        upClockwise();
                    } else if (turn == '-') {
                        upClockwise();
                        upClockwise();
                        upClockwise();
                    }
                    return;
                case 'D':
                    if (turn == '+') {
                        downClockwise();
                    } else if (turn == '-') {
                        downClockwise();
                        downClockwise();
                        downClockwise();
                    }
                    return;
            }

            // 큐브의 앞면을 시계 혹은 반시계로 회전
            switch (turn) {
                case '+':
                    clockwise();
                    break;
                case '-':
                    counterClockwise();
                    break;
            }

            // 큐브를 다시 원래대로 큐브 자체를 회전
            switch (op) {
                case 'F':
                    break;
                case 'B':
                    leftToFrontTurn();
                    leftToFrontTurn();
                    break;
                case 'L':
                    leftToFrontTurn();
                    leftToFrontTurn();
                    leftToFrontTurn();
                    break;
                case 'R':
                    leftToFrontTurn();
                    break;
            }
        }

        // 앞면을 시계방향으로 돌리는 메서드
        public void clockwise() {
            char[] L_temp = {L[0][2], L[1][2], L[2][2]};
            L[0][2] = D[0][0];
            L[1][2] = D[0][1];
            L[2][2] = D[0][2];

            D[0][0] = R[2][0];
            D[0][1] = R[1][0];
            D[0][2] = R[0][0];

            R[0][0] = U[2][0];
            R[1][0] = U[2][1];
            R[2][0] = U[2][2];

            U[2][0] = L_temp[2];
            U[2][1] = L_temp[1];
            U[2][2] = L_temp[0];

            char[][] turnFront = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    turnFront[i][j] = F[2 - j][i];
                }
            }
            F = turnFront;
        }

        // 앞면을 반시계방향으로 돌리는 메서드
        public void counterClockwise() {
            clockwise();
            clockwise();
            clockwise();
        }

        // 윗면을 시계방향으로 돌리는 메서드
        public void upClockwise() {
            char[] F_temp = {F[0][0], F[0][1], F[0][2]};
            F[0][0] = R[0][0];
            F[0][1] = R[0][1];
            F[0][2] = R[0][2];

            R[0][0] = B[0][0];
            R[0][1] = B[0][1];
            R[0][2] = B[0][2];

            B[0][0] = L[0][0];
            B[0][1] = L[0][1];
            B[0][2] = L[0][2];

            L[0][0] = F_temp[0];
            L[0][1] = F_temp[1];
            L[0][2] = F_temp[2];

            char[][] turnUp = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    turnUp[i][j] = U[2 - j][i];
                }
            }
            U = turnUp;
        }

        // 아래면을 시계방향으로 돌리는 메서드
        public void downClockwise() {
            char[] F_temp = {F[2][0], F[2][1], F[2][2]};
            F[2][0] = L[2][0];
            F[2][1] = L[2][1];
            F[2][2] = L[2][2];

            L[2][0] = B[2][0];
            L[2][1] = B[2][1];
            L[2][2] = B[2][2];

            B[2][0] = R[2][0];
            B[2][1] = R[2][1];
            B[2][2] = R[2][2];

            R[2][0] = F_temp[0];
            R[2][1] = F_temp[1];
            R[2][2] = F_temp[2];

            char[][] turnDown = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    turnDown[i][j] = D[2 - j][i];
                }
            }
            D = turnDown;
        }

        // 왼쪽 면이 앞면으로 오도록 큐브 자체를 회전하는 메서드
        public void leftToFrontTurn() {
            char[][] F_temp = new char[3][3];
            copy(F_temp, F);

            copy(F, L);
            copy(L, B);
            copy(B, R);
            copy(R, F_temp);

            char[][] turnUp = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    turnUp[i][j] = U[j][2 - i];
                }
            }
            U = turnUp;

            char[][] turnDown = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    turnDown[i][j] = D[2 - j][i];
                }
            }
            D = turnDown;
        }

        private void copy(char[][] desc, char[][] src) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    desc[i][j] = src[i][j];
                }
            }
        }
    }
}
