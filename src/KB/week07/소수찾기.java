package KB.week07;

/* ================================================================
 *
 * Problem  : PGS - 소수찾기
 * Author   : 김로아
 * Date     : 2025-06-18
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 한 자리 숫자가 적힌 종이 조각들이 있다.
 * 종이 조각을 붙여 만들 수 있는 소수는 몇 개일까?
 *
 * # 입력
 * 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers
 *
 * # 출력
 * 종이 조각으로 만들 수 있는 소수 개수
 *
 * 💻 알고리즘 설계
 * 1) 재귀를 사용한 완전 탐색을 하며 종이 조각들로 만들 수 있는 수의 모든 경우(curr)를 구함
 * 2) 소수인지 판별하는 함수 구현
 * 3) curr을 listToInt 함수에 넣어, 하나의 숫자로 병합
 * 4) 소수라면, primes 소수 set에 저장
 * (curr 조합에서 같은 숫자가 나올 가능성 때문 ex. 011, 11 -> 11 중복)
 * 5) set 크기 리턴
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class 소수찾기 {
    Set<Integer> primes = new HashSet<>();

    public static boolean isPrime(int n) {
        if (n < 2) return false; // 0, 1은 소수가 아님

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false; // 나누어 떨어지면 소수가 아님
        }
        return true;
    }

    public int solution(String numbers) {
        int[] digits = new int[numbers.length()];
        // 문자열을 하나씩 떼서 정수 배열로 저장
        for (int i = 0; i < numbers.length(); i++) {
            digits[i] = numbers.charAt(i) - '0';
        }

        boolean[] visited = new boolean[digits.length];
        recur(digits, visited, new ArrayList<>());
        return primes.size();
    }

    public void recur(int[] digits, boolean[] visited, List<Integer> curr) {
        int num = listToInt(curr);
        if (num != -1 && isPrime(num)) {
            primes.add(num);
        }
        for (int i = 0; i < digits.length; i++) {
            if (!visited[i]) {
                curr.add(digits[i]);
                visited[i] = true;
                recur(digits, visited, curr);
                curr.remove(curr.size() - 1);
                visited[i] = false;
            }
        }
    }

    public int listToInt(List<Integer> curr) {
        if (curr.isEmpty()) return -1;
        StringBuilder sb = new StringBuilder();
        for (int num : curr) {
            sb.append(num);
        }
        return Integer.parseInt(sb.toString());
    }
}