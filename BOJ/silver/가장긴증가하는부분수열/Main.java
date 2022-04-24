package silver.가장긴증가하는부분수열;

import java.io.*;
import java.util.*;


// https://www.acmicpc.net/problem/11053
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] tokens = br.readLine().split(" ");

		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(tokens[i]);
			int idx = Collections.binarySearch(list, val);
			if (idx < 0)
				idx = (idx + 1) * (-1); // 이 원소가 추가될 위치
			
			if (idx < list.size() && list.get(idx) != null)
				list.set(idx, val);
			else
				list.add(idx, val);
		}
		System.out.println(list.size());

	}

}
