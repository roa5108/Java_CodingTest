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
 * # 입력
 *
 * # 출력
 *
 * 💻 알고리즘 설계
 *
 *
 * ⏰ 시간복잡도
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