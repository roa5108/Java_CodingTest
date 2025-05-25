package KB.week01;

/* ================================================================
 *
 * Problem  : PGS - 올바른 괄호
 * Author   : 김로아
 * Date     : 2025-05-25
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 올바른 괄호 -> '(' 문자로 열렸으면 반드시 ')' 문자로 닫혀야 함
 * 올바른 괄호인지 검사
 *
 * # 입력
 * '(' 또는 ')' 로만 이루어진 문자열 s
 *
 * # 출력
 * 올바른 괄호 -> true, 아니면 false
 *
 * 💻 알고리즘 설계
 * 1) 주어진 문자열을 char 배열로 바꿔서 하나씩 스택에 넣기.
 * 2) '('면 스택에 넣음
 * 3) 스택이 비어있지 않고, ')'면 스택에서 pop
 * 4) 스택이 비어있으면 false
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.ArrayDeque;
import java.util.Deque;

class 올바른_괄호 {
    boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.add(ch);
            } else if (!stack.isEmpty()) {
                stack.remove();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}