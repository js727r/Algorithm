#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool graph[101][101];
void F_W(int n)
{
    for (int k = 1; k <= n; k++)
    {
        for (int a = 1; a <= n; a++)
        {
            if (a == k || !graph[a][k])
                continue;

            for (int b = 1; b <= n; b++)
            {
                if (b == k)
                    continue;
                if (graph[k][b])
                    graph[a][b] = true;
            }
        }
    }
}
int solution(int n, vector<vector<int>> results) {
    int answer = 0;
    for (int i = 0; i < results.size() ; i++)
    {
        int a = results[i][0], b = results[i][1];
        graph[a][b] = true;
    }

    F_W(n);

    for (int a = 1; a <= n; a++)
    {
        int win = 0, lose = 0;
        for (int b = 1; b <= n; b++)
        {
            if (graph[a][b])
                win++;
            if (graph[b][a])
                lose++;

        }
        if (win + lose == n-1)
        {
            answer++;
        }
    }


    return answer;
}