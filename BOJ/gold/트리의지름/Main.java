package gold.트리의지름;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static class Node {
		int vertex;
		int weight;
		Node link;

		public Node(int vertex, int weight, Node link) {
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}

	}

	static Node[] node;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		node = new Node[n + 1];

		for (int i = 1; i < n; i++) {
			String[] token = br.readLine().split(" ");
			int from, to, w;
			from = Integer.parseInt(token[0]);
			to = Integer.parseInt(token[1]);
			w = Integer.parseInt(token[2]);
			node[from] = new Node(to, w, node[from]);
			node[to] = new Node(from, w, node[to]);
		}

		int max = 0;
		// DFS 시작
		for (int i = 1; i <= n; i++) {
			max = Math.max(max, dfs(i,new boolean[n+1]));
		}

		System.out.println(max);

	}

	public static int dfs(int vertex, boolean[] visited) {
		visited[vertex] = true;

		if (node[vertex] == null)
			return 0;

		int max = 0;
		for (Node current = node[vertex]; current != null; current = current.link) {
			if (!visited[current.vertex])
				max = Math.max(max, current.weight+dfs(current.vertex, visited));
		}

		return max;

	}

}
