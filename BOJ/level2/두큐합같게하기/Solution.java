package BOJ.level2.두큐합같게하기;

import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int length = queue1.length;
        int answer = -1;
        // queue 1,2 합 계산
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0,sum2 = 0;

        for(Integer i : queue1) {
            sum1+=i;
            q1.offer(i);
        }
        for(Integer i : queue2) {
            sum2+=i;
            q2.offer(i);
        }

        // 합이 더 큰 쪽에서 pop
        boolean find = true;
        int count = 0;
        while (sum1 != sum2) {
            if (sum1 < sum2) {
                int out = q2.poll();
                sum2 -= out;
                sum1 += out;
                q1.offer(out);
            } else {
                int out = q1.poll();
                sum1 -= out;
                sum2 += out;
                q2.offer(out);
            }
            count++;
            if (count>length*3) {
                find = false;
                break;
            }
        }
        if (find)
            answer = count;
        // 같아질때까지 반복
        return answer;
    }
}