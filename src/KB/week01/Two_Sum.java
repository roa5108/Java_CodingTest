package KB.week01;

/* ================================================================
 *
 * Problem  : LeetCode - Two Sum
 * Author   : 김로아
 * Date     : 2025-05-15
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 주어진 nums 배열에서 두 수를 더해 target을 만들어야 함.
 * 동일한 수는 다시 사용할 수 없음.
 *
 * # 입력
 * 정수 배열 nums, 목표 수 target
 * nums = [2,7,11,15], target = 9
 *
 * # 출력
 * 두 수의 인덱스 값 배열
 * [0,1]
 *
 * 💻 알고리즘 설계
 * 이중 for문을 돌면서 (i, j=i+1) 인덱스를 겹치지 않게 설정 후 더하기
 *
 * ⏰ 시간복잡도
 * O(N^2) - N은 배열 길이
 * ================================================================
 */

class Two_Sum {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}