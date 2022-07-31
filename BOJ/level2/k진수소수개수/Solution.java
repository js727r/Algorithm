package level2.k진수소수개수;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        // k진수로 변환        
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.insert(0,n%k);
            n /= k;
        }
        
        String kNum = sb.toString();
        sb = new StringBuilder();
        for (int i = 0; i<kNum.length();i++) {
            char d = kNum.charAt(i);
            if (d == '0') {
                // check sb is prime
                if (isPrime(sb.toString())) {
                    ++answer;
                }
                sb = new StringBuilder();
            } else {
                sb.append(d);
            }
        }
        // last token check
        if (isPrime(sb.toString())) {
            ++answer;
        }
        
        return answer;
    }
    
    public boolean isPrime(String numStr) {
        if (numStr.length() == 0)
            return false;
        
        long n = Long.parseLong(numStr);
        if (n <= 1)
            return false;
        
        for (int i = 2; i<=(int)Math.sqrt(n); i++) {
            if (n % i == 0) {
              return false;
            }
        }
    	return true;
    }
}