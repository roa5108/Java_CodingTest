package KB.week08;

import java.util.Arrays;

/* ================================================================
 *
 * Problem  : LeetCode - Longest Increasing Subsequence
 * Author   : 김로아
 * Date     : 2025-06-26
 *
 * ================================================================
 * 📌 문제 분석 요약
 *
 * 가장 긴 증가하는 부분 수열(LIS)을 구하는 문제.
 * 수열 내에서 일부 원소를 골라 오름차순으로 만들 수 있는 가장 긴 길이를 반환.
 *
 * # 입력
 * - 정수 배열 nums (1 ≤ nums.length ≤ 2500)
 * - 각 원소는 정수이며 범위는 제한 없음
 *
 * # 출력
 * - 증가하는 부분 수열의 최대 길이 (int)
 *
 * 💻 알고리즘 설계
 *
 * 1) dp[i]를 i번째 수를 마지막 원소로 가지는 LIS의 길이라고 정의
 * 2) dp[i]는 nums[0] ~ nums[i-1] 중 nums[j] < nums[i]인 j에 대해
 *    dp[i] = max(dp[i], dp[j] + 1)
 * 3) 모든 i에 대해 dp[i]를 갱신한 뒤, dp 배열에서 최댓값 반환
 *
 * ⏰ 시간복잡도
 * - O(n^2)
 *   → 이중 for문 사용: i는 1~n, j는 0~i → 전체 비교 횟수는 대략 n^2
 *
 * ================================================================
 */

class Longest_Increasing_Subsequence {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); //1로 초기화

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        //최대 길이 구하기
        int maxLen = 0;
        for (int len : dp) {
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}