package silver.떡먹는호랑이;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int D, K;
		Scanner sc= new Scanner(System.in);
		D = sc.nextInt();
		K = sc.nextInt();
		
		int dp[] = new int[D+1];
		long fibo[] = new long[D];
		fibo[1] = 1;
		fibo[2] = 1;
		for(int i = 3; i<D;i++)
			fibo[i] = fibo[i-2]+fibo[i-1];
		
		long a = 1;
		long b = 1;
		while (a <= K) {
			long rest = K-fibo[D-2]*a;
			if (rest%fibo[D-1] == 0) {
				b = rest/fibo[D-1];
				break;
			} else {
				a++;
			}
		}
		System.out.println(a);
		System.out.println(b);
	}

}
