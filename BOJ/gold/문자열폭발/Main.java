package gold.문자열폭발;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder line = new StringBuilder(sc.nextLine());
		String bomb = sc.nextLine();
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < line.length(); i++) {
			answer.append(line.charAt(i));
			// 폭탄 검출
			if (detectBomb(answer, bomb)) {
				answer.replace(answer.length() - bomb.length(), answer.length(), "");
			}
		}
		if (answer.length() == 0)
			System.out.println("FRULA");
		else
			System.out.println(answer);

	}

	public static boolean detectBomb(StringBuilder line, String bomb) {
		int len = line.length();
		int bombLen = bomb.length();
		for (int i = 0; i < bombLen; i++) {
			if (len-1-i < 0 || line.charAt(len - 1 - i) != bomb.charAt(bombLen - 1 - i))
				return false;

		}
		return true;
	}

}
