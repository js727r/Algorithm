package BOJ.level3.등산코스정하기;
// https://school.programmers.co.kr/learn/courses/30/lessons/118669?language=java

import java.util.*;


class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // tc 2
        int n = 7;
        int[][] paths = new int[][]{
                {1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}
        };
        int[] gates = new int[]{1};
        int[] summits = new int[]{2, 3, 4};
        int[] result = sol.solution(n, paths, gates, summits);

        System.out.println("result[0]+\",\"+result[1] = " + result[0] + "," + result[1]);

        // tc 1
//        int n = 6;
//        int[][] paths = new int[][]{
//                {1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}
//        };
//
//        int[] gates = new int[]{1,3};
//        int[] summits = new int[]{5};
//        int[] result = sol.solution(n, paths, gates, summits);
//
//        System.out.println("result[0]+\",\"+result[1] = " + result[0] + "," + result[1]);
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};

        // path를 통해 그래프 생성(연결리스트)
        Node[] graph = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new Node();
        }
        for (int i = 0; i < paths.length; i++) {
            int from = paths[i][0];
            int to = paths[i][1];
            int weight = paths[i][2];
            graph[from].addPath(to, weight);
            graph[to].addPath(from, weight);
        }
        // gates 설정
        for (int i = 0; i < gates.length; i++) {
            int idx = gates[i];
            graph[idx].type = NODE_TYPE.GATE;
        }
        // summits 설정
        Arrays.sort(summits);
        for (int i = 0; i < summits.length; i++) {
            int idx = summits[i];
            graph[idx].type = NODE_TYPE.SUMMIT;
        }
        // 시작점 설정 반복
        for (int s : summits) {
            for (Edge e : graph[s].list) {
                int[] result = dfs(e.to, answer[1], e.weight, graph, new boolean[n + 1]);
                if (result[0] == -1) continue;

                if ((result[1] < answer[1])) {
                    answer[0] = s;
                    answer[1] = result[1];
                }

            }

        }

        return answer;
    }

    public enum NODE_TYPE {GATE, SPOT, SUMMIT}

    public class Node {
        public List<Edge> list = new LinkedList<>();
        public NODE_TYPE type = NODE_TYPE.SPOT;
        public int dw = Integer.MAX_VALUE; // 이 정점 이후로 임의의 도착지까지 최대 intensity 저장
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
     * @param current   현재 지점
     * @param threshold 이 값보다 작거나 같은 경로만 탐색
     * @param visit     정점 방문 여부
     */
    public static int[] dfs(int current, int threshold, int currentMax, Node[] graph, boolean[] visit) {
        if (visit[current]) return new int[]{-1, -1};   // 방문한 지점이면 종료

        if (graph[current].type == NODE_TYPE.SUMMIT) return new int[]{-1, -1}; // 현재 지점이 봉우리면 종료
        else if (graph[current].type == NODE_TYPE.GATE) {   // 목적지일 경우
            return new int[]{current, currentMax};
        }
        visit[current] = true;

        int[] min = {-1, currentMax};
        // 인접한 지점 방문
        for (Edge e : graph[current].list) {
            if (e.weight >= threshold || Math.max(e.weight,currentMax) >= graph[e.to].dw) continue; // 진행하는 의미가 없다면 다음정점

            int[] result = dfs(e.to, threshold, Math.max(currentMax, e.weight), graph, visit);    // dfs 탐색으로 루트의 최소 intensity 계산

            if (result[0] == -1) continue;  // 루트가 완성되지 않으면 다음으로

            if (min[0] == -1
            || (result[1] < min[1])){    // 루트가 완성된 경우 최소값 갱신
//                    || (result[1] == min[1] && result[0] < min[0])) {
                min = result;
            }

        }
        if (min[0] != -1) {
            graph[current].dw = min[1];
        }
        visit[current] = false;
        return min;


    }


}