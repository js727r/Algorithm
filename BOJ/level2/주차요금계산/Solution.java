package level2.주차요금계산;

import java.util.*;
import java.util.Map.Entry;

class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println("in :" +sol.convertToMin("07:59"));
		System.out.println("in :" +sol.convertToMin("19:09"));
//		System.out.println(sol.solution(new int[]{180,5000,10,600}, new String[]{"05:34 5961 IN", "06:35 5961 OUT"}));
		System.out.println(Arrays.toString(sol.solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"})));
	}
	class Bill {
		int car, cost;

		public Bill(int car, int cost) {
			super();
			this.car = car;
			this.cost = cost;
		}

	}

	public int[] solution(int[] fees, String[] records) {
		int[] answer;

		Map<Integer, String> history = new HashMap<>(); // 입차기록 map
		Map<Integer,Integer> accTime = new HashMap<>(); // 누적 이용 시간 map
		Map<Integer, Integer> calcHistory = new HashMap<>(); // 정산 map
		PriorityQueue<Bill> pq = new PriorityQueue<Bill>((o1,o2)->o1.car-o2.car); // 결과 출력을 위한 pq
		for (String record : records) {
			// 문자열 파싱
			String tokens[] = record.split(" ");
			String timeInput = tokens[0];
			int car = Integer.parseInt(tokens[1]);
			String method = tokens[2];

			if (method.equals("IN")) {
				// 입차
				history.put(car, timeInput);
			} else {
				// 출차 및 누적시간 갱신
				String inTime = history.get(car);
				int time = convertToMin(timeInput)-convertToMin(inTime);
				Integer prev = accTime.putIfAbsent(car, time);
				if (prev != null) {
					accTime.put(car, prev+time);
				}
				history.remove(car);
			}

		}
		
		// 강제 출차
		for (Entry<Integer, String> it : history.entrySet()) {
			int car = it.getKey();
			int time = convertToMin("23:59")-convertToMin(it.getValue());
			Integer prev = accTime.putIfAbsent(car, time);
			if (prev != null) {
				accTime.put(car, prev+time);
			}
		}
		
		// 정산
		for (Entry<Integer, Integer> it : accTime.entrySet()) {
			int car = it.getKey();
			int min = it.getValue();
			int fee = calcFee(min,fees);
			Integer cal = calcHistory.putIfAbsent(car, fee);
			if (cal != null) {
				calcHistory.put(car, cal + fee);
			}
		}
		// 정산기록 결과출력
		for (Entry<Integer, Integer> it : calcHistory.entrySet()) {
			pq.offer(new Bill(it.getKey(),it.getValue()));
		}
		int len = pq.size();
		answer = new int[len];
		for (int i = 0; i<len;i++) {
			answer[i] = pq.poll().cost;
		}
		
		return answer;
	}

	public int calcFee(int min, int[] fees) {
		int use = min - fees[0];
		if (use < 0) { // 기본요금
			return fees[1];
		} else { // 추가요금
			return fees[1] + (int) (Math.ceil((double) use / fees[2]) * fees[3]);
		}
	}

	public int convertToMin(String time) {
		// 파싱하여 분단위로 변환
		String[] tokens = time.split(":");
		int h = Integer.parseInt(tokens[0]);
		int m = Integer.parseInt(tokens[1]);
		return h * 60 + m;
	}
}