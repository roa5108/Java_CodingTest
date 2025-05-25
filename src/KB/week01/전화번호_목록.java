package KB.week01;

/* ================================================================
 *
 * Problem  : PGS - 전화번호 목록
 * Author   : 김로아
 * Date     : 2025-05-25
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 한 번호가 다른 번호의 접두어인 경우를 찾자
 *
 * # 입력
 * 전화번호 배열 phone_book
 *
 * # 출력
 * 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를, 그렇지 않으면 true
 *
 * 💻 알고리즘 설계
 * 1) 전화번호 문자열 배열을 사전순으로 정렬
 * 2) startsWith 함수를 사용해 현재 번호가 다음 번호의 접두어인지 확인
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.Arrays;

class 전화번호_목록 {
    public boolean solution(String[] phone_book) {
        // 사전순으로 정렬
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) return false;
        }
        return true;
    }
}