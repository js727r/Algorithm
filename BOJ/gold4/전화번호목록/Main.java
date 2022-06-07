package gold4.전화번호목록;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			Trie trie = new Trie();
			boolean isValid = true;
			for (int i = 0; i < n; i++) {
				String number = br.readLine();
				isValid &= trie.insert(number);
			}
			if (isValid)
				System.out.println("YES");
			else
				System.out.println("NO");
		}

	}

	public static class Trie {
		Node root;

		public Trie() {
			root = new Node();
		}

		public boolean insert(String number) {
			int len = number.length();
			Node current = root;
			for (int i = 0; i < len; i++) {
				int idx = number.charAt(i)-'0';
				if (current.child[idx] == null) {
					current.child[idx] = new Node();
					current.count++;
				} else if (current.child[idx].isEnd){
					return false;
				}
				current = current.child[idx];
			}
			current.isEnd = true;
			return current.count == 0 ? true : false;
		}
		
		public static class Node {
			Node[] child;
			boolean isEnd;
			int count;
			public Node() {
				child = new Node[10];
				isEnd = false;
				count = 0;
			}
		}
	}

}
