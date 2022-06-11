package silver1.히오스프로게이머;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		K = Integer.parseInt(tokens[1]);
		
		int[] X = new int[N];
		for (int i = 0; i < N; i++)
			X[i] = Integer.parseInt(br.readLine());
		
		
		if(N == 1) {
			System.out.println(X[0]+K);
			return;
		}
		
		Arrays.sort(X);
		
		int tmp = 0;
		int i;
		for(i = 1;i<N;i++) {
			int gap = X[i]-X[i-1];
			tmp+= gap*i;
			if (tmp>K)
				break;
		}
		
		
		if (tmp<=K) { // 모두 같은레벨에 도달했으나 더 레벨업 가능한 수치가 남은 경우
			int more = (K-tmp)/N;
			System.out.println(X[N-1]+more);
		} else { // 같은레벨에 도달하기 전에 레벨업을 다써버린경우 
			int error = (int)Math.ceil((tmp-K)/(double)i);
			System.out.println(X[i]-error);
		}
		
		
	}

}
