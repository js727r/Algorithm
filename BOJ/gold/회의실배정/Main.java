package gold.회의실배정;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Course {
		int s, t;

		public Course(int s, int t) {
			super();
			this.s = s;
			this.t = t;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		
		// 회의 시작시간을 기준으로 정렬
		PriorityQueue<Course> startQ = new PriorityQueue<>((o1,o2)->o1.s-o2.s);
		
		for (int i = 0;i<N;i++) {
			String[] tokens = br.readLine().split(" ");
			int s = Integer.parseInt(tokens[0]);
			int t = Integer.parseInt(tokens[1]);
			startQ.offer(new Course(s, t));
		}
		
		// 회의가 일찍 끝나는 기준으로 큐에 담음
		PriorityQueue<Course> endQ = new PriorityQueue<>((o1,o2)->o1.t-o2.t);
		
		// 각 회의 시작시간보다 일찍 끝나는 회의는 큐에서 방출
		while (!startQ.isEmpty()) {
			Course s_current = startQ.poll();
			
			while (!endQ.isEmpty()) {
				Course e_current = endQ.peek();
				if (e_current.t <= s_current.s) {
					endQ.poll();
				} else {
					break;
				}
			}
			// 큐에 넣고 사이즈 측정
			endQ.offer(s_current);
			answer = Math.max(answer, endQ.size());
		}
		
		System.out.println(answer);
		
	}

}
