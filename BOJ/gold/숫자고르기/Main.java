package gold.숫자고르기;
// https://www.acmicpc.net/problem/2668

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] select;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];   // 배열 크기 생성
        select = new boolean[N + 1];    // 최종적으로 선택한 숫자 배열

        for (int i = 1; i <= N; i++)   // 배열 입력
            arr[i] = Integer.parseInt(br.readLine());

        // 0번 인덱스부터 N-1번 인덱스에 대해 dfs 탐색을 진행
        for (int i = 1; i <= N; i++)
            dfs(i, 0, new boolean[N+1]);
        
        // 결과 배열에 저장
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i<= N;i++)
            if (select[i]) result.add(i);

        // 결과 출력
        System.out.println(result.size());
        for (int num : result) {
            System.out.println(num);
        }
    }

    // 타고타고 들어가면서 깔끔하게 완성된 경우 결과에 반영
    public static void dfs(int idx, int flag, boolean[] visit) {
        if (select[idx])    // 이미 결과에 반영된 지점 방문시 종료
            return;

        if (visit[idx]) {   // 방문했던 점을 다시 방문한 경우
            if (flag != 0)  // 일치하지 않는 경우
                return;
            // 현재 방문한 결과를 선택결과에 반영
            for (int i = 1;i<visit.length;i++)
                select[i] = visit[i]? true : select[i];

        }
        else {
            visit[idx] = true;
            flag ^= idx^arr[idx];
            dfs(arr[idx],flag,visit);
        }
    }
}
