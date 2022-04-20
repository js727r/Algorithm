package gold.암호코드;

// https://www.acmicpc.net/problem/2011
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		int len = word.length();
		int[] dp = new int[len+1];
		
		
		dp[0] = 1;
		if (word.charAt(0) != '0')
			dp[1] = 1;
		
		for (int i = 2; i <= len; i++) {
			char c = word.charAt(i-1);
			char f = word.charAt(i-2);
			if (c != '0')
				dp[i] = dp[i-1];
			
			int num = (f-'0')*10+(c-'0');
			if (num>=10 && num<=26)
				dp[i] += dp[i-2];
			
			dp[i] %= 1000000;
		}
		System.out.println(dp[len]);
	}
}
// 121074110 : 2