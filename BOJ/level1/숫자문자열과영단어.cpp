// https://school.programmers.co.kr/learn/courses/30/lessons/81301
#include <string>
#include <map>
using namespace std;
map<string, int> sn;

int isNumberString(const string& s)
{
    map<string, int>::iterator siter = sn.find(s);

    if (siter == sn.end())
        return -1;

    return siter->second;
}
bool isDigit(const string& s)
{
    for (const char& c : s)
    {
        if (std::isdigit(c) == 0)
            return false;
    }
    return true;
}
int pushNumber(int origin, int a_num)
{
    return origin * 10 + a_num;
}


int solution(string s) {
    int answer = 0;

    sn.insert({ "zero",0 });
    sn.insert({ "one", 1 });
    sn.insert({ "two", 2 });
    sn.insert({ "three", 3 });
    sn.insert({ "four", 4 });
    sn.insert({ "five", 5 });
    sn.insert({ "six", 6 });
    sn.insert({ "seven", 7 });
    sn.insert({ "eight", 8 });
    sn.insert({ "nine", 9 });



    for (int i = 0; i< s.length();i++)
    {
        string mlc;
        mlc.push_back(s[i]);
        if (isDigit(mlc))
        {
            answer = pushNumber(answer, stoi(mlc,nullptr));

        }
        else
        {
            int stringInt;
            while ((stringInt = isNumberString(mlc)) == -1)
            {
                mlc.push_back(s[++i]);
            }
            answer = pushNumber(answer, stringInt);
        }
    }

    return answer;
}