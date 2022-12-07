package BOJ.level2.피로도;

class Solution {
    int answer = 0;
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        int n = dungeons.length;
        perm(dungeons,n,0,0,new int[n],k);
        return answer;
    }
    public void perm(int[][] dungeons,int n,int count, int flag,int[] arr,int k) {
        if (count == n) {
            // 모든 던전 순열 구하기완료
            int finished = 0;
            for (int i = 0; i<n;i++) {
                // 최소조건 만족하는지
                if (k < dungeons[arr[i]][0]) break;
                // 던전 탐색 및 피로도 감소
                k -= dungeons[arr[i]][1];
                finished++;
            }
            answer = Math.max(answer,finished);
        } else {
            for (int i = 0; i<n;i++) {
                if ((flag & 1<<i) != 0) continue;
                arr[count] = i;
                perm(dungeons,n,count+1,flag|1<<i,arr,k);
            }
        }
    }

}