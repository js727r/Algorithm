def solution(survey, choices):
    answer = ''
    # 점수테이블 넣기
    # 각 성격유형별 점수 초기화
    result = dict()
    result['R'] = 0
    result['T'] = 0
    result['C'] = 0
    result['F'] = 0
    result['J'] = 0
    result['M'] = 0
    result['A'] = 0
    result['N'] = 0
    # 선택별 점수 넣어주기
    for idx, val in enumerate(choices):
        val -= 4;
        if val < 0:
            at = 0
        else:
            at = 1
        result[survey[idx][at]] += abs(val)
    # 최종 성격유형 출력
    answer += 'R' if result['R'] >= result['T'] else 'T'
    answer += 'C' if result['C'] >= result['F'] else 'F'
    answer += 'J' if result['J'] >= result['M'] else 'M'
    answer += 'A' if result['A'] >= result['N'] else 'N'
return answer