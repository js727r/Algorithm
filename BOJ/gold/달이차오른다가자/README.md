# 아이디어

1. 6종류의 키 `a,b,c,d,e` 를 6개의 비트를 활용하여 `set` 하는 방식으로 현재 보유중인 열쇠를 저장한다.
2. `a = 1, b = 10, c = 100 ...` 이 된다. 열쇠는 6종류이므로 key값의 범위는 `0~63`이 된다.
3. BFS 방식으로 배열을 탐색한다.
4. 현재 보유중인 열쇠의 상태에 따라 왔던길을 되돌아가야 하는 경우도 생기므로, `visit = new Boolean[R][C][key]` 로 선언한다. ( `0<=key<64` )
5. 문을 만났을 경우 현재 보유중인 키값과 비트 연산을 통해 지나갈 수 없는지를 판단한다.

# 풀이

```java
// 키를 얻는 함수
public void obtainKey(char key) {
    this.key |= 1 << (key - 'a');
}
// 문에 도착했을 때 키를 보유하고있는지 확인
public boolean hasKey(char door) {
    if ((key & (1 << (door - 'A'))) != 0)
        return true;
    return false;
}
```
