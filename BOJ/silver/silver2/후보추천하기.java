import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1713

public class Main {
	static class Frame implements Comparable<Frame> {
		int student;
		int frameNum;

		public Frame(int student, int frameNum) {
			super();
			this.student = student;
			this.frameNum = frameNum;
		}

		@Override
		public int compareTo(Frame o) {
			return frameNum - o.frameNum;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, S;
		N = Integer.parseInt(br.readLine());
		S = Integer.parseInt(br.readLine());
		int recommand[] = new int[101];

		List<Frame> frames = new ArrayList<Frame>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int frameNum = 0;
		for (int i = 0; i < S; i++) {
			int student = Integer.parseInt(st.nextToken());
			if (recommand[student] == 0) { // 학생의 액자가 없다면 추가
				// 빈 액자가 없다면
				if (frames.size() == N) {
					// 추천이 가장 적은 학생의 액자 삭제 + 추천수 초기화
					Collections.sort(frames);
					int min = Integer.MAX_VALUE;
					int minIdx = -1;
					int frameIdx = 0;
					for (int j = 0; j < N; j++) {
						int reco = recommand[frames.get(j).student];
						if (reco < min) {
							min = reco;
							minIdx = frames.get(j).student;
							frameIdx = j;
						}
					}
					frames.remove(frameIdx);
					recommand[minIdx] = 0;
				}
				// 학생 액자 추가
				frames.add(new Frame(student, frameNum++));
			}
			// 학생의 액자에 추천수 추가
			recommand[student]++;
		}
		Collections.sort(frames, (o1, o2) -> o1.student - o2.student);
		StringBuilder sb = new StringBuilder();
		for (Frame f : frames) {
			sb.append(f.student).append(" ");
		}
		System.out.println(sb);
	}

}
