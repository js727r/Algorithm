package BOJ.level3.가장먼노드;

import java.util.*;
class Solution {
    class Node {
        int vertex;
        int dist;
        ArrayList<Integer> link;
        public Node(int vertex) {
            this.vertex = vertex;
            link = new ArrayList<>();
            dist = -1;
        }
        public void add(int to) {
            link.add(to);
        }
    }


    public int solution(int n, int[][] edge) {
        int answer = 0;
        // 노드 리스트
        Node[] list = new Node[n+1];
        for (int i = 1; i<=n;i++) {
            list[i] = new Node(i);
        }
        // 그래프 생성
        for (int i = 0; i<edge.length;i++) {
            int from = edge[i][0];
            int to = edge[i][1];
            list[from].add(to);
            list[to].add(from);
        }
        // 방문 체크
        boolean[] visited = new boolean[n+1];
        // 가장 먼 거리
        int maxDistance = 0;

        // bfs 진행

        Queue<Integer> queue = new LinkedList<>();

        list[1].dist = 0;
        visited[1] = true;

        queue.add(1);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int d = list[current].dist;
            for (int i = 0; i<list[current].link.size();i++) {
                int vertex = list[current].link.get(i);
                if (!visited[vertex]) { // 방문하지 않았을경우
                    visited[vertex] = true;
                    maxDistance = Math.max(maxDistance,d+1);
                    list[vertex].dist = d+1;

                    queue.add(vertex);
                }
            }
        }
        // 배열에서 dist가 max값인것 카운팅
        for (int i = 1; i<=n;i++) {
            if (list[i].dist == maxDistance) {
                answer++;
            }
        }

        return answer;
    }
}