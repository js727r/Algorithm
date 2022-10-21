package toss;

import java.util.*;
import java.util.Map.Entry;

public class Solution4 {

	public static void main(String[] args) {
		long[] answer = solution(new long[][] {{1L,2L},{2L,3L}});
		System.out.println(Arrays.toString(answer));
	}
	static class InvitationScore implements Comparable<InvitationScore>{
		long id;
		int lastInvite;
		int score;
		public InvitationScore(long id,int score,int last) {
			this.id = id;
			this.score = score;
			this.lastInvite = last;
		}
		@Override
		public int compareTo(InvitationScore o) {
			if (o.score == this.score)
				return this.lastInvite-o.lastInvite;
			
			return o.score - this.score;
		}
		@Override
		public String toString() {
			return "InvitationScore [id=" + id + ", score=" + score + "]";
		}
		
	}
    public static long[] solution(long[][] invitationPairs) {
        long[] answer;
        
        // 직접 초대한 사람응 저장하는 맵
        Map<Long,List<Long>> map = new HashMap();
        // 마지막 초대가 몇 번째인지 저장
        Map<Long,Integer> lastInvite = new HashMap();
        
        
        for( int i = 0; i<invitationPairs.length;i++) {
        	long[] pair = invitationPairs[i];
        	// 초대한 사람 목록 추가
        	List<Long> list = map.putIfAbsent(pair[0], new ArrayList<Long>());
        	if (list == null)
        		list = map.get(pair[0]);
        	
        	list.add(pair[1]);
        	lastInvite.put(pair[0], i);
        }
        
        // 각 사람의 점수 계산 및 결과 저장
        PriorityQueue<InvitationScore> rank = new PriorityQueue<>();
        
        for(Entry<Long, List<Long>> entry : map.entrySet()) {
        	int score = getScore(entry.getKey(),map,0);
        	rank.offer(new InvitationScore(entry.getKey(), score,lastInvite.get(entry.getKey())));
        }
        
        answer = new long[Math.min(3,invitationPairs.length)];
        for (int i = 0; i<answer.length;i++) {
        	answer[i] = rank.poll().id;
        }
        return answer;
    }
    
    public static int getScore(Long id, Map<Long,List<Long>> map, int depth) {
    	int indirectScore = 0;
    	int mul;
    	if (depth == 0)
    		mul = 10;
    	else if (depth == 1)
    		mul = 3;
    	else 
    		mul = 1;
    	
    	List<Long> invitations = map.get(id);
    	if (invitations == null)
    		return mul;
    	
    	for (Long inv : invitations) {
    		if (depth < 2)
    			indirectScore += getScore(inv, map, depth+1);
    	}
    	
    	return mul*invitations.size()+indirectScore;
    }

}
