package BOJ.level3.등산코스정하기;
// https://school.programmers.co.kr/learn/courses/30/lessons/118669?language=java

import java.util.*;

import static BOJ.level3.등산코스정하기.Solution.NODE_TYPE.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        // path를 통해 그래프 생성(연결리스트)
        Node[] nodelist = new Node[n + 1];
        for (int i = 0; i < paths.length; i++) {
            int from = paths[i][0];
            int to = paths[i][1];
            int weight = paths[i][2];
            nodelist[from].addPath(to, weight);
            nodelist[to].addPath(from, weight);
        }
        // gates 설정
        for (int i = 0; i < gates.length; i++) {
            int idx = gates[i];
            nodelist[idx].type = GATE;
        }
        // summits 설정
        for (int i = 0; i < summits.length; i++) {
            int idx = gates[i];
            nodelist[idx].type = SUMMIT;
        }
        // 시작점, 봉우리 설정 반복
        for (int s : gates) {
            for (int e : summits) {
                dfs(s,e,Integer.MAX_VALUE,new boolean[n+1]);
            }
        }
        // 시작점-봉우리 루트 계산 반복 (dfs로 진행하는데 이 봉우리가 나올때까지함)
        // 최소 intensity보다 긴 루트는 건너뜀
        // 루트 완성 시 이 시작점-봉우리의 최소 intensity 업데이트

        return answer;
    }

    public enum NODE_TYPE {GATE, SPOT, SUMMIT}

    public class Node {
        public List<Edge> list = new LinkedList<>();
        public NODE_TYPE type = SPOT;

        public void addPath(int to, int weight) {
            list.add(new Edge(to, weight));
        }
    }

    public class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     *
     * @param current 현재 지점
     * @param dest 목적지
     * @param threshold 이 값보다 작거나 같은 경로만 탐색
     * @param visit 정점 방문 여부
     */
    public static void dfs(int current,int dest, int threshold, boolean[] visit) {

    }

    public static boolean isSummit(int n) {
        return false;
    }
}