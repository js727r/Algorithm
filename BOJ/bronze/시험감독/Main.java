package bronze.시험감독;
// https://www.acmicpc.net/problem/13458

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N, B, C;
        N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < A.length; i++) {
            A[i] = sc.nextInt();
        }
        B = sc.nextInt();
        C = sc.nextInt();

        long answer = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] - B <= 0) {
                answer++;
            } else {
                int q = (A[i] - B) / C;
                int r = (A[i] - B) % C;
                if (r != 0)
                    q++;
                answer += q + 1;
            }
        }
        System.out.println(answer);
    }
}