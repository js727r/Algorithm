package gold4.구간나누기2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st= new StringTokenizer(br.readLine());
		
		int min = Integer.MAX_VALUE;
		int max = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		
		binary(0,max-min,arr);
		System.out.println(result);
	}
	
	static void binary(int left, int right, int[] arr) {
		if (left>right)
			return;
		
		int mid = (left+right)/2;
		
		int count = 1;
		int min = arr[0];
		int max = arr[0];
		int score = 0;
		for (int i = 0; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
			score = Math.max(max-min, score);
			if (score > mid) {
				count++;
				min = arr[i];
				max = arr[i];
				score = 0;
			}
		}
		if (count<=M) { // 구간합의 최대가 mid가되는 구간나누기 성공
			result = Math.min(result, mid);
			// 구간합을 줄여서 다시 시도
			binary(left,mid-1,arr);
		} else {
			// 구간합을 늘려서 다시 시도
			binary(mid+1,right,arr);
		}
	}

}
