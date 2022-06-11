package gold.수묶기;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> pos = new ArrayList<>();
		ArrayList<Integer> neg = new ArrayList<>();
		boolean zero = false;
		for (int i = 0; i < N; i++) {
			int in = Integer.parseInt(br.readLine());
			if (in == 0)
				zero = true;
			else if (in < 0)
				neg.add(in);
			else
				pos.add(in);

		}

		Collections.sort(pos, Collections.reverseOrder());
		Collections.sort(neg);
//		System.out.println(Arrays.toString(pos.toArray()));
//		System.out.println(Arrays.toString(neg.toArray()));
		int answer = 0;
		for (int i = 0; i < pos.size(); i+= 2) {
			if (i == pos.size() - 1) {
				answer += pos.get(i);
			} else {
				int tmpMul = pos.get(i) * pos.get(i + 1);
				int tmpSum = pos.get(i) + pos.get(i + 1);
				answer += Math.max(tmpMul, tmpSum);
			}
		}

		for (int i = 0; i < neg.size(); i+= 2) {
			if (i == neg.size() - 1) {
				if (!zero)
					answer += neg.get(i);
			} else {
				int tmpMul = neg.get(i) * neg.get(i + 1);
				answer += tmpMul;
			}
		}

		System.out.println(answer);
	}

}
