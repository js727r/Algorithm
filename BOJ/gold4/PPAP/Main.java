package gold4.PPAP;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	static Stack<Character> stack = new Stack<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		int len = input.length();
		
		boolean isPPAP = true;
		for (int i = 0; i < len; i++) {
			char c = input.charAt(i);
			if (stack.isEmpty()) {
				stack.push(c);
				continue;
			}
			char peek = stack.peek();
			if (c == 'P') {
				if (peek == 'A') {
					// PPAP check
					if (isPPAP()) {
						stack.push('P');
					} else {
						isPPAP = false;
						break;
					}
				} else {
					stack.push(c);
				}
			} else { // 입력이 A인경우
				if (peek == 'A') {
					isPPAP = false;
					break;
				} else {
					stack.push(c);
				}
			}
		}
		
		if (isPPAP && stack.size() == 1 && stack.peek().equals('P'))
			System.out.println("PPAP");
		else 
			System.out.println("NP");
	}
	static boolean isPPAP() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			if (stack.isEmpty())
				return false;
			
			sb.append(stack.pop());
		}
		
		return sb.toString().equals("APP");
	}
}
