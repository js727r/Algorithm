package toss;

public class Solution1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public int solution(String s) {
        int answer = -1;
        
        int chainCount = 0;
        char chainLetter= 'a';
        for (int i= 0 ;i<s.length();i++) {
        	char read = s.charAt(i);
        	if (chainCount == 0) {
        		chainLetter = read;
        		chainCount++;
        	} else {
        		if (read == chainLetter) {
        			chainCount++;
        		} else {
        			chainLetter = read;
        			chainCount = 1;
        		}
        	}
        	if (chainCount == 3)
        		answer = Math.max(answer, chainLetter-'0');
        	
        	
        }
        
        return answer > 0 ? answer*111 : answer;
    }

}
