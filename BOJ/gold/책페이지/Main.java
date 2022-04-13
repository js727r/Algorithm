package gold.책페이지;

import java.util.Scanner;

// 책 페이지 https://www.acmicpc.net/problem/1019
public class Main {

    static long ans[] = new long[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = 1;
        long b = sc.nextLong();

        long point = 1; // 자릿수
        while (a <= b) {
            // 일의자리 맞춰주기
            while (a % 10 != 0 && a <= b) {
                calc(a++, point);
            }
            if (a > b)
                break;

            while (b % 10 != 9 && a <= b) {
                calc(b--, point);
            }

            a /= 10;
            b /= 10;
            for (int i = 0; i < 10; i++) {
                ans[i] += (b - a + 1) * point;
            }
            point *= 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    static void calc(long n, long point) {
        while (n > 0) {
            ans[(int) (n % 10)] += point;
            n /= 10;
        }
    }

}
