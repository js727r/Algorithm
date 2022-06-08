package gold.구간합구하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K;
	static long arr[];
	static long seg[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1]; // 1번부터 시작
		seg = new long[N*4]; // 1번부터 시작
		
		for (int i = 0; i < N; i++) {
			arr[i+1] = Long.parseLong(br.readLine());
		}
		
		initTree(1,N,1);
		
		for (int i = 0; i < M+K; i++) {
			String[] tokens = br.readLine().split(" ");
			int a,b;
			a = Integer.parseInt(tokens[0]);
			b = Integer.parseInt(tokens[1]);
			
			if (a==1) { // 숫자 바꾸기
				long c = Long.parseLong(tokens[2]);
				long dif = c-arr[b];
				arr[b] = c;
				updateTree(1,N,1,b,dif);
			} else { // 구간합 구하기
				int c = Integer.parseInt(tokens[2]);
				System.out.println(getRange(1,N,1,b,c));
			}
		}
		
	}
	
	public static long initTree(int from, int to, int node) {
		// 구간합 트리 생성
		if (from == to)
			return seg[node] = arr[from];
		int mid = (from+to)/2;
		return seg[node] = initTree(from,mid,node*2)+initTree(mid+1,to,node*2+1);
		
	}
	
	public static void updateTree(int from,int to, int node, int index, long value) {
		// 특정 값 수정후 구간합 트리 업데이트
		
		if (index < from || index>to) { // 바뀌지 않는 노드는 스킵
			return;
		}
		
		seg[node] += value;
		
		if (from==to) // 리프노드에서 종료
			return;
		
		// 왼쪽과 오른쪽 서브트리 업데이트
		int mid = (from+to)/2;
		updateTree(from,mid,node*2,index,value);
		updateTree(mid+1,to,node*2+1,index,value);
	}
	
	public static long getRange(int from, int to, int node, int left,int right) {
		// from부터 to까지의 합 구하기
		
		// 구하려는 범위가 이 노드의 범위에 들어있지 않은경우
		if (left>to || right<from)
			return 0;
		
		// 구하려는 범위 안에 온전히 포함되는경우
		if (left<=from && right>=to)
			return seg[node];
		
		int mid = (from+to)/2;
		return getRange(from,mid,node*2,left,right)+getRange(mid+1,to,node*2+1,left,right);
	}
}
