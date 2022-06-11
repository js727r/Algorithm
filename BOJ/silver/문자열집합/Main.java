package silver.문자열집합;

import java.io.*;

public class Main {
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		Trie trie = new Trie();
		for (int i = 0; i < N; i++)
			trie.insert(br.readLine());
		
		int answer = 0;
		for (int i = 0; i < M; i++)
			if (trie.find(br.readLine()))
				answer++;
		
		System.out.println(answer);
	}

	static final int ALPHABET_LENGTH = 26;

	public static class Trie {
		Node root;

		public Trie() {
			this.root = new Node();
			this.root.val = ' ';
		}

		public void insert(String str) {
			// 입력으로 들어온 각 문자를 루트부터 내려가면서 인서트
			int len = str.length();
			Node current = this.root;
			for (int i = 0; i < len; i++) { // 0번째 문자부터 끝까지 반복
				char c = str.charAt(i); // 문자열의 i번째 문자
				int childIdx = charToInt(c); // 자식배열의 인덱스

				if (current.child[childIdx] == null)// i번째 문자 원소가 없다면 추가
					current.child[childIdx] = new Node(c);

				current = current.child[childIdx]; // 다음 원소로 내려가기
			}
			current.isEnd = true; // 마지막 문자임을 체크
		}
		
		public boolean find(String str) {
			// 입력으로 들어온 각 문자를 루트부터 내려가면서 찾기
			int len = str.length();
			Node current = root;
			for (int i = 0; i < len; i++) {
				char c = str.charAt(i);
				int childIdx = charToInt(c);
				if (current.child[childIdx] == null)
					return false;
				
				current = current.child[childIdx];
			}
			
			return current.isEnd;
		}
		private int charToInt(char c) {
			return c - 'a';
		}
	}

	public static class Node {
		char val;
		Node[] child = new Node[ALPHABET_LENGTH];
		boolean isEnd;

		public Node() {
			this.val = ' ';
			isEnd = false;
		}

		public Node(char c) {
			this();
			this.val = c;
		}
	}
}
