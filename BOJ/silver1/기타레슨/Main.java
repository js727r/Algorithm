package silver1.기타레슨;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int rec[] = new int[N];
		int total = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			rec[i] = Integer.parseInt(st.nextToken());
			total += rec[i];
		}

		// 최소값 = 총 길이/블루레이 수
		int min = (int) Math.ceil(total / (double) M);
		// 최대값 = 총 길이
		int max = total;
		binary(min,max,rec);
		System.out.println(result);
	}
	static void binary(int l, int r, int[] rec) {
		if (l>r)
			return;
		// 중간값 = 블루레이 임시크기
		int mid = (l + r) / 2;
		// mid값으로 다 담을 수 있는지 테스트

		int count = 1;
		int current = 0;
		boolean success = false;
		int i = 0;
		for (; i < N && count<=M; i++) {
			current += rec[i];
			if (current > mid) {
				count++;
				i--;
				current = 0;
			}
		}
		if (i ==N && count <= M) {
			success=true;
		}
		if (success) { // 여유공간이 남는경우
			binary(l,mid-1,rec);
			result = Math.min(result, mid);
		} else { // 크기를 늘려야 하는 경우
			binary(mid+1,r,rec);
		}
	}
	
}
