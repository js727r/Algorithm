package silver.퇴사;

import java.io.*;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int consulting[][] = new int[N][2];
		int dp[] = new int[N + 1];
		for (int i = 0; i < N; i++) {
			String line[] = br.readLine().split(" ");
			consulting[i][0] = Integer.parseInt(line[0]);
			consulting[i][1] = Integer.parseInt(line[1]);
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			int t = consulting[i][0];
			int p = consulting[i][1];
			if (i + t <= N) {
				if (dp[i + t] < dp[i] + p) {
//					Arrays.fill(dp, i + t, N + 1, dp[i] + p);
					for (int j = i+t; j <= N; j++) {
						dp[j] = Math.max(dp[j], dp[i]+p);
					}
				}
				answer = Math.max(answer, dp[i + t]);
			}
		}
		System.out.println(answer);

	}

}
