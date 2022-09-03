package silver.십자카드문제;
// https://www.acmicpc.net/problem/2659


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int num = Integer.parseInt(tokens[0]) * 1000
                + Integer.parseInt(tokens[1]) * 100
                + Integer.parseInt(tokens[2]) * 10
                + Integer.parseInt(tokens[3]) * 1;

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        int min = getMinimumClockNum(num);

        // 가능한 모든 시계수 찾기
        Set<Integer> set = new HashSet<>();
        for (int start = 1111; start <= 9999; start++) {
            if (hasZero(start)) continue;
            int clockNum = getMinimumClockNum(start);
            set.add(clockNum);

        }
        List<Integer> list = new LinkedList<>(set);
        Collections.sort(list);
        int answer = list.indexOf(min)+1;

        System.out.println(answer);
    }

    public static boolean hasZero(int num) {
        while (num > 0) {
            int r = num % 10;
            if (r == 0)
                return true;

            num /= 10;
        }
        return false;
    }

    public static int getMinimumClockNum(int num) {
        char[] digits = new char[4];
        for (int i = 0; i < 4; i++) {
            int digit = num % 10;
            digits[3-i] = (char) ('0' + digit);
            num /= 10;
        }
        int minimumClockNumber = Integer.MAX_VALUE; // 입력으로 주어진 최소 시계수
        for (int start = 0; start < 4; start++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                int idx = (start + i) % 4;
                sb.append(digits[idx]);
            }
            minimumClockNumber = Math.min(minimumClockNumber, Integer.parseInt(sb.toString()));
        }
        return minimumClockNumber;
    }
}
