package toss;

import java.util.Arrays;

public class Solution2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    public int solution(int[] levels) {
        int len = levels.length;
        int target = len/4;
        
        Arrays.sort(levels);
        
        return target== 0? -1 : levels[len-target];
    }
    
}
