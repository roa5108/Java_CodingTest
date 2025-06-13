package KB.week07;

/* ================================================================
 *
 * Problem  : LeetCode - Two Sum
 * Author   : 김로아
 * Date     : 2025-06-13
 *
 * ================================================================
 * 📌 문제 분석 요약
 * nums 정수 배열이 주어지고, target 숫자가 주어짐.
 * nums 배열에서 두 수의 합이 target이 되는 두 수를 구하기 (중복 X)
 *
 * # 입력
 * nums 정수 배열, target 숫자
 * Input: nums = [2,7,11,15], target = 9
 *
 * # 출력
 * 정답인 두 수를 배열로 리턴
 * Output: [0,1]
 *
 * 💻 알고리즘 설계
 * 배열을 for문으로 돌다가, target에서 각 수를 뺀 나머지가 map에 있으면 정답
 * 없으면 현재 값과 인덱스를 map에 저장
 *
 * ⏰ 시간복잡도
 * O(n), n은 배열 길이
 * ================================================================
 */

import java.util.HashMap;
import java.util.Map;

class Two_Sum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1};
    }
}