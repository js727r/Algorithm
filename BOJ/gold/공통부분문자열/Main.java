package gold.공통부분문자열;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word1 = sc.nextLine();
		String word2 = sc.nextLine();
		int[][] dp = new int[word1.length()][word2.length()];
		int max = 0;
		for (int w1 = 0; w1 < word1.length(); w1++) {
			char c1 = word1.charAt(w1);
			for (int w2 = 0; w2 < word2.length(); w2++) {
				char c2 = word2.charAt(w2);

				if (c1 == c2) {
					if (w1 != 0 && w2 != 0) {
						dp[w1][w2] = dp[w1 - 1][w2 - 1] + 1;
					} else {
						dp[w1][w2] = 1;
					}
				}
				max = Math.max(dp[w1][w2], max);
			}
		}
		System.out.println(max);
	}
}
