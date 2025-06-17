package PGS.Lv2;

/* ================================================================
 *
 * Problem  : PSG 타겟 넘버
 * Author   : 김로아
 * Date     : 2025-05-08
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 정수들을 순서 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들기
 *
 * # 입력
 * 사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target
 *
 * # 출력
 * 타겟 넘버를 만드는 방법의 수 return
 *
 * 💻 알고리즘 설계
 * 1) dfs로, 현재 인덱스에서 숫자를 더하거나 빼는 두 가지 선택지를 재귀적으로 탐색한다.
 * 2) 인덱스가 numbers.length에 도달했을 때 누적 합(sum)이 target과 같다면, 경우의 수(answer)를 1 증가시킨다.
 * 3) dfs 호출 시 인덱스를 하나씩 증가시키며 sum에 현재 숫자를 더하거나 뺀 값을 넘긴다.
 * 4) 초기 dfs는 index 0부터, 누적합 0으로 시작
 *
 * ⏰ 시간복잡도
 * O(2^N)
 * ================================================================
 */

class 타겟넘버 {
    static int answer = 0;

    public static void dfs(int[] numbers, int index, int sum, int target) {
        if (index == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        dfs(numbers, index + 1, sum + numbers[index], target);
        dfs(numbers, index + 1, sum - numbers[index], target);
    }

    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, 0, target);

        return answer;
    }
}