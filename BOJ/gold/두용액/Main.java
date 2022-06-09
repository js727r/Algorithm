package gold.두용액;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long arr[] = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		System.out.println(Arrays.toString(arr));

		int left = 0;
		int right = arr.length - 1;

		long liq1 = arr[left];
		long liq2 = arr[right];

		long min = Long.MAX_VALUE;
		while (left < right) {
			long combined = arr[left] + arr[right];

			if (Math.abs(combined) < min) {
				liq1 = arr[left];
				liq2 = arr[right];
				min = Math.abs(combined);
				if (combined == 0)
					break;
			}
			if (combined < 0) {
				left++;
			} else {
				right--;
			}

		}

		System.out.printf("%d %d", liq1, liq2);
	}

}
