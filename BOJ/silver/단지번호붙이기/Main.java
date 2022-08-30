package silver.단지번호붙이기;
// silver 1 단지번호 붙이기 https://www.acmicpc.net/problem/2667

import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());           // 가로 세로 크기
        arr = new int[N][N];    // 초기 배열 생성

        for (int i = 0; i < N; i++) {   // 아파트 배열 입력 받기
            String line = sc.nextLine();
            for (int j = 0; j < N; j++)
                arr[i][j] = line.charAt(j)-'0';
        }
        List<Integer> result = new ArrayList<>();   // 결과 배열
        int dangi = -1;  // 연결된 아파트 단지의 번호
        for (int i = 0; i < N * N; i++) {  // dfs의 시작점 탐색
            int r = i / N;
            int c = i % N;
            if (arr[r][c] == 1) {
                int apart_num = dfs(r, c, dangi);
                result.add(apart_num);
                dangi--;
            }
        }
        Collections.sort(result);   // 오름차순 정렬
        System.out.println(result.size());  // 총 단지수
        for(int num : result) { // 단지별 아파트 수 오름차순 출력
            System.out.println(num);
        }
    }
    // 배열에 저장한 후 오름차순으로 정렬

    static int dfs(int r, int c, int dangi) {
        if (!isValid(r, c) || arr[r][c] != 1)    // 유효하지 않은 좌표거나 방문했을경우 종료
            return 0;

        int apart_num = 1; // 인접한 아파트의 수
        arr[r][c] = dangi;  // 방문 처리
        for (int d = 0; d < 4; d++) {   // 인접 지점 탐색
            apart_num += dfs(r+dr[d],c+dc[d],dangi);
        }
        return apart_num;
    }

    static boolean isValid(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= N)
            return false;
        return true;
    }
}
