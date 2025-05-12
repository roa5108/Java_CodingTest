package PGS.Lv2;

import java.util.Arrays;

/* ================================================================
 *
 * Problem  : PGS - 구명보트
 * Author   : 김로아
 * Date     : 2025-05-12
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 무인도에 갇힌 사람들을 구명보트로 구출해야함. 한 번에 최대 2명씩, 무게 제한 O
 * 구명보트를 최대한 적게 사용해 모든 사람을 구출하려 함.
 *
 * # 입력
 * 사람들의 몸무게를 담은 배열 people, 구명보트 무게 제한 limit
 *
 *  people	         limit
 * [70, 50, 80, 50]	  100
 *
 * # 출력
 * 모든 사람을 구출하기 위해 필요한 구명보트의 최소 개수
 *
 * 💻 알고리즘 설계
 * 투 포인터를 사용하면 쉽다 ! (이중 for문 써보았으나 더 어렵고, 되는지 모르겠음)
 * 1) 오름차순으로 무게 배열 정렬
 * 2) 가장 가벼운 사람, 가장 무거운 사람 둘이 합쳐서 limit 이하면 둘 다 태움 (left++, right--)
 * 3) 그게 아니라면, 무거운 사람 혼자 태움 (right--)
 * 4) 보트는 항상 1개 쓰므로 count++
 *
 * ⏰ 시간복잡도
 * O(nlogn) - n은 배열 길이
 *
 * Arrays.sort() -> O(nlogn)
 * while 루프 -> O(n)
 * ================================================================
 */

class P_구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int boat = 0;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            boat++;
        }
        return boat;
    }
}
