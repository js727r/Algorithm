package gold.공유기설치;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,C;
	static int result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[] h = new int[N];
		for (int i = 0; i < N; i++) {
			h[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(h);
		
		// 공유기의 간격을 몇으로 설정할지 Binary search 진행
		
		int left = 1;
		int right = h[N-1]-h[0];
		binary(left,right,h);
		System.out.println(result);
	}
	static void binary(int left,int right,int[] h) {
		if (left>right)
			return;
		int mid = (left+right)/2;
		int count = 1; // h[0]에는 무조건 설치
		int prevX = h[0];
		for (int i = 1; i < N && count<C; i++) {
			int gap = h[i]-prevX;
			if (gap>=mid) { // 설치
				count++;
				prevX=h[i];
			}
		}
		if (count == C) { // 전부 설치 가능
			// 이때의 값을 결과와 비교
			result = Math.max(result, mid);
			// 간격을 늘려서 다시 시도
			binary(mid+1,right,h);
		} else { // 전부 설치하지 못함
			// 간격을 줄이기
			binary(left,mid-1,h);
		}
	}

}
