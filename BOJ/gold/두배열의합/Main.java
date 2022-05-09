package gold.두배열의합;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int A[] = new int[n];
		List<Long> a = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		
		
		int m  = Integer.parseInt(br.readLine());
		int B[] = new int[m];
		List<Long> b = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// A의 구간합 구하기
		for(int left = 0; left<n;left++) {
			long sum = 0;
			for (int right = left; right < n; right++) {
				sum+=A[right];
				a.add(sum);
			}
		}
//		System.out.println(a);
		
		// B의 구간합 구하기
		for(int left = 0; left<m;left++) {
			long sum = 0;
			for (int right = left; right < m; right++) {
				sum+=B[right];
				b.add(sum);
			}
		}
	}
}
