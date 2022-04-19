package silver.이항계수;

import java.util.*;

public class Main {

	static long fac[];
	static final int mod = 10007;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N, R;
		N = sc.nextInt();
		R = sc.nextInt();
		fac = new long[N + 1];
		fac[0] = 1;
		for (int i = 1; i <= N; i++) {
			fac[i] = fac[i - 1] * i % mod;
		}
		// nCr
		long answer = nCr(N, R, mod);
		System.out.println(answer);

	}

	static long nCr(int n, int r, int p) {
		if (r == 0)
			return 1L;

		return (fac[n] * power(fac[r], p - 2, p) % p * power(fac[n - r], p - 2, p) % p) % p;
	}

	static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p;
		while (y > 0) {
			if (y % 2 == 1)
				res = (res * x) % p;
			y = y >> 1;
			x = (x * x) % p;
		}
		return res;
	}
}