package gold.파일합치기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] dp;
	static int[] sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			dp = new int[K][K];
			sum = new int[K];
			String[] line = br.readLine().split(" ");
			for (int i = 0; i < line.length; i++) {
				if (i == 0)
					sum[i] = Integer.parseInt(line[i]);
				else
					sum[i] = sum[i-1]+Integer.parseInt(line[i]);
			}
			
			int min = getMin(0,K-1);
			System.out.println(min);
		}
	}
	
	static int getMin(int from, int to) {
		if (from == to)
			return 0;
		if (dp[from][to] != 0)
			return dp[from][to];
		
		int min= Integer.MAX_VALUE;
		for (int k = from; k < to; k++) {
			int cal = getMin(from,k)+getMin(k+1,to) + sum[to];
			if (from>0)
				cal -= sum[from-1];
			min = Math.min(min,cal);
		}
		dp[from][to] = min;
		return min;
	}

}
