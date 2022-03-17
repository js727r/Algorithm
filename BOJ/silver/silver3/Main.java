// https://www.acmicpc.net/problem/2805

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		tree[N] = 0;

		// 이분탐색 진행
		Arrays.sort(tree);

		System.out.println(cut(tree[0], tree[tree.length - 1]));
	}

	public static int cut(int l, int r) {

		if (l > r)
			return r;
		// 자를 높이 지정
		int h = (l + r) / 2;
		// 잘리는 나무의 시작 인덱스 구하기
		int m = Arrays.binarySearch(tree, h) + 1;
		if (m < 0)
			m = Math.abs(m);

		// 이 높이로 얻을 수 있는 길이 구하기
		long cut = 0;
		for (int i = m; i < N+1; i++) {
			cut += tree[i] - h;
		}
		// 자른 나무가 필요한 양보다 적으면
		if (cut < M)
			return cut(l, h-1);
		else
			return cut(h+1, r);
	}

}
