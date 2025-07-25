package KB.week01;

/* ================================================================
 *
 * Problem  : PGS - 주식가격
 * Author   : 김로아
 * Date     : 2025년 07월 15일
 * 
 * ================================================================
 * 📌 문제 분석 요약
 * 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때,
 * 가격이 떨어지지 않은 기간은 몇 초인지를 return
 *
 * # 입력
 * - prices 배열
 *
 * # 출력
 * - 각 초의 가격이 떨어지지 않은 기간이 몇 초인지 담은 배열
 *
 * 💻 알고리즘 설계
 * 1. 이중 for문으로 i초와 i+1초의 가격을 비교
 * 2. 가격이 떨어졌으면 count 증가를 멈추고
 * 3. 그 이외에는 1초마다 count를 증가시켜 ans 배열에 대입
 * 4. ans 리턴
 *
 * ⏰ 시간복잡도
 * - O(n^2) 이중 for문
 * ================================================================
 */

class 주식가격 {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];

        for(int i=0; i<n; i++){
            int count=0;
            for(int j=i+1; j<n; j++){ // i초와 i+1초의 가격을 비교
                count++;
                if(prices[i]>prices[j]) break; // 다음 초에 가격이 떨어졌으면 count 증가 멈춤
            }
            ans[i]=count; // 초마다 증가한 count를 정답 배열에 대입
        }

        return ans;
    }
}