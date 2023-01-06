/*
    Boj 3053. 택시 기하학
    level. bronze 3
    solved by 송찬환
 */
package boj_bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_3053_택시기하학 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double r = Double.parseDouble(br.readLine());

        System.out.printf("%.6f\n", Math.PI * r * r);
        System.out.printf("%.6f\n", 2 * r * r);
    }
}
