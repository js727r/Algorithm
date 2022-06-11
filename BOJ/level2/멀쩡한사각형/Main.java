package level2.멀쩡한사각형;

public class Main {

	public static void main(String[] args) {
		Solution2 sol = new Solution2();
		System.out.println(sol.solution(8, 12));
		System.out.println(sol.solution(12, 8));
	}

	static class Solution {
	    public long solution(int w, int h) {
	    	long answer = w*h;
	    	if (w>h) {
	    		int tmp = w;
	    		w = h;
	    		h = tmp;
	    	}
	    	double a = (double)h/w; // 기울기
	    	
	    	int px = 0;
	    	long count = 1;
	    	for (int y = 1; y < h; y++) {
				// y가 정수가 되는 x좌표 구하기
	    		double double_x = ((double)y/w*h);
	    		int int_x = (int)double_x;
	    		count++; // y값기준 윗쪽 사각형 자르기
	    		
	    		if (int_x !=double_x) { // 정수가 아니면 아랫쪽 사각형 고려
	    			if (px != int_x) { // 중복되지 않는 사각형 자르기
	    				count++;
	    			}
	    		}
	    		px = int_x;
			}
	        return answer-count;
	    }
	}
	
	static class Solution2 {
	    public long solution(int w, int h) {
	    	long answer = (long)w*(long)h;
	    	long count = 0;
	    	for (int x = 0; x<w;x++) {
	    		double upTmp = (long)((double)h*(x+1))/(double)w;
	    		double loTmp = (long)((double)h*(x))/(double)w;
	    		int upper = (int) Math.ceil(upTmp);
	    		int lower = (int) Math.floor(loTmp);
	    		count += upper-lower;
	    	}
	        return answer-count;
	    }
	}
}
