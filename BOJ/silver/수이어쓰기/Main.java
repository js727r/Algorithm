package silver.수이어쓰기;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N,k;
		N = sc.nextInt();
		k = sc.nextInt();
		
		int tmp = k;
		int numberLen = 1; // 숫자의 자릿수
		long numberCount = 9; // 자릿수에 해당하는 숫자의 개수
		long klen = 0; // 지나쳐온 글자수
		// k가 속하는 범위찾기
		while (tmp>numberLen*numberCount) {
			tmp -= numberLen*numberCount;
			klen += numberCount;
			numberCount*=10;
			numberLen++;
		}
		// tmp에는 앞으로 계산해야할 숫자만큼 남아있음
		
		// k번째 문자를 포함하는 숫자 구하기
		klen = (klen+1) + ((tmp-1) / numberLen);
		if (klen>N)
			System.out.println(-1);
		else {
			int idx = (tmp-1)%numberLen;
			System.out.println(String.valueOf(klen).charAt(idx));
		}
	}
	
}
