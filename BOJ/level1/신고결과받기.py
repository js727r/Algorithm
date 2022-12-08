def solution(id_list, reports, k):
    answer = []


    reported = {id:[] for id in id_list}
    answer = [0]*len(id_list)
    for report in set(reports):
        report = report.split(' ')
        reported[report[1]].append(report[0])

    for key, val in reported.items():
        if len(val) >= k:
            for v in val:
                answer[id_list.index(v)] += 1

return answer