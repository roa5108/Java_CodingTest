package KB.week07;

/* ================================================================
 *
 * Problem  : PGS - 타겟 넘버
 * Author   : 김로아
 * Date     : 2025-06-18
 *
 * ================================================================
 * 📌 문제 분석 요약
 * numbers: 정수 배열
 * 각 숫자마다 + 또는 - 중 하나를 붙일 수 있음
 * 이렇게 해서 만든 결과값의 총합이 target이 되는 경우의 수를 구하는 문제
 *
 * # 입력
 * 사용할 수 있는 숫자가 담긴 배열 numbers,
 * 타겟 넘버 target
 *
 * # 출력
 * 타겟 넘버를 만드는 방법의 수
 *
 * 💻 알고리즘 설계
 * 각 요소마다 두 가지 결정(+ 또는 -)만 하는 이진 트리 구조
 * => for문이 필요 없고, 단순히 왼쪽 가지(+)와 오른쪽 가지(-) 로 재귀를 나누는 방식
 * 1) 배열의 끝까지 도달했을 때 (depth == 배열 길이) 누적된 sum == target인 경우,
 * 1을 반환해서 경우의 수로 세고 다르면 0 반환해서 세지 않음
 * 2) 더하는 경우와 빼는 경우 두가지 분기를 만들어 다음 depth로 재귀 호출
 * 3) 각 경우의 수에서 target 도달 여부 판단, 마지막에 plus + minus로 최종적으로 가능한 경우의 수 모두 합산
 *
 * ⏰ 시간복잡도
 * O(2*n), n은 numbers 크기
 * ================================================================
 */

class 타겟넘버 {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }

    public int dfs(int[] numbers, int depth, int sum, int target) {
        if (depth == numbers.length) {
            return sum == target ? 1 : 0;
        }

        int plus = dfs(numbers, depth + 1, sum + numbers[depth], target);
        int minus = dfs(numbers, depth + 1, sum - numbers[depth], target);
        return plus + minus;
    }
}