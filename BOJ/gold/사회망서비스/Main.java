package gold.사회망서비스;

import java.io.*;

public class Main {
	static class Node {
		int vertex;
		Node link;

		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}

	}

	static Node[] adjList;
	static boolean[] visit;
	static boolean[] early;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adjList = new Node[N + 1];
		visit = new boolean[N + 1];
		early = new boolean[N + 1];
		for (int i = 0; i < N - 1; i++) {
			String[] tokens = br.readLine().split(" ");
			int u, v;
			u = Integer.parseInt(tokens[0]);
			v = Integer.parseInt(tokens[1]);
			adjList[u] = new Node(v, adjList[u]);
			adjList[v] = new Node(u, adjList[v]);
		}

		// 임의의 정점 1번부터 dfs 진행
		dfs(1);
		System.out.println(answer);
	}

	static void dfs(int node) {
		visit[node] = true;
		int non_early = 0;
		for (Node temp = adjList[node]; temp != null; temp = temp.link) {

			if (!visit[temp.vertex]) {
				dfs(temp.vertex);
				if (!early[temp.vertex])
					non_early++;
			}

		}
		if (non_early != 0) { 
			early[node] = true;
			answer++;
		}

	}

}
